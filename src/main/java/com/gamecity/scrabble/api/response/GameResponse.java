package com.gamecity.scrabble.api.response;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class GameResponse
{
    private int responseCode;
    private String responseMessage;
    private Object data;

    public GameResponse()
    {
        this.responseCode = 200;
    }

    public GameResponse(int responseCode, String responseMessage)
    {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public GameResponse(Object data)
    {
        this.data = data;
        this.responseCode = 200;
    }

    public int getResponseCode()
    {
        return responseCode;
    }

    public void setResponseCode(int responseCode)
    {
        this.responseCode = responseCode;
    }

    public String getResponseMessage()
    {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage)
    {
        this.responseMessage = responseMessage;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
