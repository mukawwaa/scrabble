package com.gamecity.scrabble.service.impl;

import static com.gamecity.scrabble.Constants.HTTP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.gamecity.scrabble.Constants;
import com.gamecity.scrabble.model.AuthToken;
import com.gamecity.scrabble.model.UserLogin;
import com.gamecity.scrabble.service.RestService;
import com.gamecity.scrabble.util.JsonUtils;
import com.sun.jersey.core.util.Base64;

@Component
public class RestServiceImpl implements RestService
{
    @Autowired
    private RestService restService;

    @Value("${auth.api}")
    private String authApi;

    @Value("${rest.api}")
    private String restApi;

    @SuppressWarnings("static-access")
    @Cacheable(value = Constants.Cache.AUTHENTICATION, key = "{#login.username, #login.password}")
    public AuthToken authenticate(UserLogin login)
    {
        try
        {
            StringBuilder authLink =
                new StringBuilder()
                    .append(authApi).append("?grant_type=password").append("&username=").append(login.getUsername()).append("&password=")
                    .append(login.getPassword());
            URL url = new URL(authLink.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            String basicAuth = "Basic " + new String(new Base64().encode(Constants.AUTH_CREDENTIALS.getBytes()));
            conn.setRequestProperty("Authorization", basicAuth);
            conn.setRequestMethod(HTTP.Method.POST.name());
            conn.setRequestProperty("Accept", "application/json");

            String jsonString = buildResponse(conn);
            return JsonUtils.convertToEntity(jsonString, AuthToken.class);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T getSingleEntityWithAuth(String resource, UserLogin login, Class<T> clazz, Object... params)
    {
        String jsonString = callJsonGetServiceWithAuth(resource, login, params);
        return JsonUtils.convertToEntity(jsonString, clazz);
    }

    @Override
    public <T> List<T> getEntityListWithAuth(String resource, UserLogin login, Class<T> clazz, Object... params)
    {
        String jsonString = callJsonGetServiceWithAuth(resource, login, params);
        return JsonUtils.convertToEntityList(jsonString, clazz);
    }

    @Override
    public <T> T postEntityWithAuth(String resource, UserLogin login, Class<T> clazz, Object item, Object... params)
    {
        String jsonString = callJsonPostServiceWithAuth(resource, login, item, params);
        return JsonUtils.convertToEntity(jsonString, clazz);
    }

    @Override
    public <T> List<T> postEntityReturnListWithAuth(String resource, UserLogin login, Class<T> clazz, Object item, Object... params)
    {
        String jsonString = callJsonPostServiceWithAuth(resource, login, item, params);
        return JsonUtils.convertToEntityList(jsonString, clazz);
    }

    // ---------------------------------------------------- private methods ----------------------------------------------------

    private String callServiceWithAuth(String resource, UserLogin login, HTTP.Method method, Map<String, String> props, Object item, Object... params)
    {
        try
        {
            HttpURLConnection conn = createConnection(resource, login, params);
            conn.setRequestMethod(method.name());
            props.entrySet().forEach(entry -> conn.setRequestProperty(entry.getKey(), entry.getValue()));
            conn.setDoOutput(HTTP.Method.POST.equals(method));

            if (HTTP.Method.POST.equals(method) && item != null)
            {
                String jsonString = JsonUtils.convertToJson(item);
                conn.setRequestProperty("Content-Length", "" + String.valueOf(jsonString.getBytes("UTF-8").length));
                DataOutputStream daos = new DataOutputStream(conn.getOutputStream());
                daos.write(jsonString.getBytes("UTF-8"));
                daos.flush();
                daos.close();
            }
            return buildResponse(conn);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private String callJsonGetServiceWithAuth(String resource, UserLogin login, Object... params)
    {
        Map<String, String> requestProperties = new HashMap<String, String>();
        requestProperties.put("Accept", "application/json");
        return callServiceWithAuth(resource, login, HTTP.Method.GET, requestProperties, null, params);
    }

    private <T> String callJsonPostServiceWithAuth(String resource, UserLogin login, T object, Object... params)
    {
        Map<String, String> requestProperties = new HashMap<String, String>();
        requestProperties.put("Accept", "application/json");
        requestProperties.put("Content-Type", "application/json");
        return callServiceWithAuth(resource, login, HTTP.Method.POST, requestProperties, object, params);
    }

    private String prapereServiceUrl(String resource, Object... params)
    {
        StringBuilder address = new StringBuilder();
        address.append(restApi).append(resource);
        if (params != null && params.length > 0)
        {
            addHttpParams(address, params);
        }

        return address.toString();
    }

    private HttpURLConnection createConnection(String resource, UserLogin login, Object... params)
    {
        AuthToken authToken = restService.authenticate(login);
        String serviceUrl = prapereServiceUrl(resource, params) + "?access_token=" + authToken.getAccess_token();

        try
        {
            URL url = new URL(serviceUrl);
            return (HttpURLConnection) url.openConnection();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private String buildResponse(HttpURLConnection conn)
    {
        try
        {
            if (conn.getResponseCode() != 200)
            {
                throw new RuntimeException(String.valueOf(conn.getResponseCode()));
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            StringBuilder jsonString = new StringBuilder();
            while ((output = br.readLine()) != null)
            {
                jsonString.append(output);
            }
            conn.disconnect();
            return jsonString.toString();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void addHttpParams(StringBuilder address, Object... params)
    {
        if (params != null && params.length > 0)
        {
            IntConsumer consumer = i -> address.replace(address.indexOf("{"), address.indexOf("}") + 1, String.valueOf(params[i]));
            IntStream.range(0, params.length).forEach(consumer);
        }
    }
}
