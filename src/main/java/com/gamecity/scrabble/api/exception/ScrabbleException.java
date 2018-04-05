package com.gamecity.scrabble.api.exception;

public class ScrabbleException extends RuntimeException
{
    private static final long serialVersionUID = -3257022882385328074L;
    private int errorCode;
    private String errorMessage;

    public ScrabbleException()
    {
        super();
    }

    public ScrabbleException(int errorCode, String errorMessage)
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
