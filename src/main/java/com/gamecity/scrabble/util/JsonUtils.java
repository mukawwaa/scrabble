package com.gamecity.scrabble.util;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.gamecity.scrabble.model.AuthToken;
import com.gamecity.scrabble.model.api.GameException;
import com.gamecity.scrabble.model.api.GameResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class JsonUtils
{
    private static Gson gsonBuilder;

    static
    {
        final GsonBuilder builder = new GsonBuilder();
        final DateFormat deserializeFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
        final DateFormat serializeFormat = new SimpleDateFormat("dd.MMM.yyyy HH:mm:ss");
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
            {
                try
                {
                    return deserializeFormat.parse(json.getAsJsonPrimitive().getAsString());
                }
                catch (ParseException e)
                {
                    return null;
                }
            }
        });
        builder.registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
            @Override
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context)
            {
                return new JsonPrimitive(serializeFormat.format(src));
            }
        });
        gsonBuilder = builder.create();
    }

    public static <T> List<T> convertToEntityList(String jsonString, Class<T> clazz)
    {
        if (jsonString == null || jsonString.trim().isEmpty() || clazz == null)
        {
            return null;
        }

        validateResponse(jsonString);

        JsonObject object = new JsonParser().parse(JsonUtils.formatJson(jsonString)).getAsJsonObject();
        JsonElement dataElement = object.get("data");
        if (dataElement == null || dataElement.isJsonNull())
        {
            return Collections.emptyList();
        }

        JsonArray array = dataElement.getAsJsonArray();
        List<T> entityList = new ArrayList<T>();
        for (JsonElement jsonElement : array)
        {
            T entity = gsonBuilder.fromJson(jsonElement, clazz);
            entityList.add(entity);
        }
        return entityList;
    }

    public static <T> T convertToEntity(String jsonString, Class<T> clazz)
    {
        if (jsonString == null || jsonString.trim().isEmpty())
        {
            return null;
        }

        if (AuthToken.class.equals(clazz))
        {
            return gsonBuilder.fromJson(jsonString, clazz);
        }

        validateResponse(jsonString);

        JsonObject object = new JsonParser().parse(JsonUtils.formatJson(jsonString)).getAsJsonObject();
        JsonElement dataElement = object.get("data");
        if (dataElement == null || dataElement.isJsonNull())
        {
            return null;
        }

        return gsonBuilder.fromJson(dataElement, clazz);
    }

    public static String convertToJson(Object item)
    {
        return JsonUtils.gsonBuilder.toJson(item);
    }

    private static void validateResponse(String jsonString)
    {
        GameResponse response = gsonBuilder.fromJson(jsonString, GameResponse.class);
        if (response.getResponseCode() != 200)
        {
            throw new GameException(response.getResponseCode(), response.getResponseMessage());
        }
    }

    private static String formatJson(String jsonString)
    {
        if (jsonString == null || jsonString.trim().isEmpty())
        {
            return null;
        }

        jsonString = jsonString.replace("\\", "");
        if (jsonString.substring(0, 1).equals("\"") && jsonString.substring(jsonString.length() - 1).equals("\""))
        {
            jsonString = jsonString.substring(1, jsonString.length() - 1);
        }
        return jsonString;
    }
}
