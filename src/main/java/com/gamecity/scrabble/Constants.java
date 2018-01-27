package com.gamecity.scrabble;

public interface Constants
{
    String CHARACTER_ENCODING = "UTF-8";
    String AUTH_CREDENTIALS = "BASE_APP_TRUSTED_CLIENT:BASE_APP_SECRET";
    Long ASYNCHRONOUS_REQUEST_DURATION = 30 * 1000L;

    interface Cache
    {
        String AUTHENTICATION = "REST_AUTHENTICATION";
    }

    interface RedisListener
    {
        String BOARD_CHAT = "BOARD_CHAT";
        String BOARD_CONTENT = "BOARD_CONTENT";
    }

    interface HTTP
    {
        enum Method
        {
            GET, POST, PUT, OPTIONS, DELETE
        }
    }

    interface Headers
    {
        String ALLOW_ORIGIN = "Access-Control-Allow-Origin";
        String ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
        String ALLOW_METHODS = "Access-Control-Allow-Methods";
        String MAX_AGE = "Access-Control-Max-Age";
        String ALLOW_HEADERS = "Access-Control-Allow-Headers";
    }
}
