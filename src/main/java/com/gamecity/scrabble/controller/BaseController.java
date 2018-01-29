package com.gamecity.scrabble.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.gamecity.scrabble.Resources;
import com.gamecity.scrabble.Resources.Services;
import com.gamecity.scrabble.model.UserLogin;
import com.gamecity.scrabble.model.api.User;
import com.gamecity.scrabble.service.RestService;

public abstract class BaseController
{
    @Value("${redis.listener.user}")
    private String redisListenerUsername;

    @Value("${redis.listener.password}")
    private String redisListenerPassword;

    @Autowired
    private RestService restService;

    // authenticated user username
    private String getUsername()
    {
        AbstractAuthenticationToken auth = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return auth == null ? null : ((User) auth.getPrincipal()).getUsername();
    }

    // authenticated user password
    private String getPassword()
    {
        Object credentials = SecurityContextHolder.getContext().getAuthentication().getCredentials();
        return credentials == null ? null : credentials.toString();
    }

    private UserLogin getUserLogin()
    {
        return new UserLogin(getUsername(), getPassword());
    }

    private UserLogin getNotificationLogin()
    {
        return new UserLogin(redisListenerUsername, redisListenerPassword);
    }

    // authenticated user id
    protected Long getUserId()
    {
        AbstractAuthenticationToken auth = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (!(auth.getPrincipal() instanceof User))
        {
            throw new RuntimeException("Session is not active!");
        }
        return ((User) auth.getPrincipal()).getId();
    }

    // table rule id
    protected Long getRuleId()
    {
        return 1L;
    }

    // get
    protected <T> T findById(String alias, Long id)
    {
        Services service = Services.valueOf(alias);
        return (T) restService.getSingleEntityWithAuth(service.resource + Resources.get, getUserLogin(), service.clazz, id);
    }

    // list
    protected <T> List<T> listByClass(String alias)
    {
        Services service = Services.valueOf(alias);
        return (List<T>) restService.getEntityListWithAuth(service.resource + Resources.list, getUserLogin(), service.clazz);
    }

    // save
    protected void saveEntity(String alias, Object entity)
    {
        Services service = Services.valueOf(alias);
        restService.postEntityWithAuth(service.resource + Resources.save, getUserLogin(), null, entity);
    }

    // post
    protected <T> T post(String resource, Class clazz, Object item, Object... params)
    {
        return (T) restService.postEntityWithAuth(resource, getUserLogin(), clazz, item, params);
    }

    protected <T> List<T> postWithListReturn(String resource, Class clazz, Object item, Object... params)
    {
        return (List<T>) restService.postEntityReturnListWithAuth(resource, getUserLogin(), clazz, item, params);
    }

    protected <T> List<T> listByCriteria(String resource, Class clazz, Object... params)
    {
        return (List<T>) restService.getEntityListWithAuth(resource, getUserLogin(), clazz, params);
    }

    protected <T> List<T> listNotificationsByCriteria(String resource, Class clazz, Object... params)
    {
        return (List<T>) restService.getEntityListWithAuth(resource, getNotificationLogin(), clazz, params);
    }

    protected <T> T getByCriteria(String resource, Class clazz, Object... params)
    {
        return (T) restService.getSingleEntityWithAuth(resource, getUserLogin(), clazz, params);
    }

    protected <T> T getNotificationByCriteria(String resource, Class clazz, Object... params)
    {
        return (T) restService.getSingleEntityWithAuth(resource, getNotificationLogin(), clazz, params);
    }
}
