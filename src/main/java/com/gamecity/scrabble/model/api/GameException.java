package com.gamecity.scrabble.model.api;

public class GameException extends RuntimeException
{
    private static final long serialVersionUID = -3257022882385328074L;
    private int errorCode;
    private String errorMessage;

    public GameException()
    {
        // base constructor
    }

    public GameException(int errorCode, String errorMessage)
    {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }
}
