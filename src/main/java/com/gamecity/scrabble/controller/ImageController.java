package com.gamecity.scrabble.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/image")
public class ImageController extends BaseController
{
    @Value("${avatar.path}")
    private String avatarPath;

    @RequestMapping(value = "/avatar/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> getAvatar(@PathVariable Long id) throws FileNotFoundException
    {
        InputStream inputStream = new BufferedInputStream(new FileInputStream(String.format(avatarPath, id)));
        InputStreamResource resource = new InputStreamResource(inputStream);
        return new ResponseEntity<InputStreamResource>(resource, HttpStatus.OK);
    }

    @RequestMapping(value = "/avatar", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> getAvatar() throws FileNotFoundException
    {
        InputStream inputStream = new BufferedInputStream(new FileInputStream(String.format(avatarPath, getUserId())));
        InputStreamResource resource = new InputStreamResource(inputStream);
        return new ResponseEntity<InputStreamResource>(resource, HttpStatus.OK);
    }
}
