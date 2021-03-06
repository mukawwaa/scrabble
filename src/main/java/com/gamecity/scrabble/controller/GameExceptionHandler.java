package com.gamecity.scrabble.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gamecity.scrabble.api.exception.ScrabbleException;
import com.gamecity.scrabble.api.response.GameResponse;
import com.gamecity.scrabble.util.JsonUtils;

@ControllerAdvice
public class GameExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleGameException(Exception e, WebRequest request)
    {
        if (e instanceof ScrabbleException)
        {
            ScrabbleException se = (ScrabbleException) e;
            GameResponse response = new GameResponse(se.getErrorCode(), se.getErrorMessage());
            return handleExceptionInternal(se, JsonUtils.convertToJson(response), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
        }
        else
        {
            return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
        }
    }
}