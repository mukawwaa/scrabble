package com.gamecity.scrabble.service;

import java.util.List;

import com.gamecity.scrabble.model.AuthToken;
import com.gamecity.scrabble.model.UserLogin;

public interface RestService
{
    AuthToken authenticate(UserLogin login);

    <T> T getSingleEntityWithAuth(String resource, UserLogin login, Class<T> clazz, Object... params);

    <T> List<T> getEntityListWithAuth(String resource, UserLogin login, Class<T> clazz, Object... params);

    <T> T postEntityWithAuth(String resource, UserLogin login, Class<T> clazz, Object item, Object... params);

    <T> List<T> postEntityReturnListWithAuth(String resource, UserLogin login, Class<T> clazz, Object item, Object... params);
}
