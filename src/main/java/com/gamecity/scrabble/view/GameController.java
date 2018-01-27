package com.gamecity.scrabble.view;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest")
public class GameController extends BaseController
{
    @RequestMapping(value = "/{alias}/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> get(@PathVariable String alias, @PathVariable Long id)
    {
        Object entity = findById(alias, id);
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }

    @RequestMapping(value = "/{alias}/list", method = RequestMethod.GET)
    public ResponseEntity<List> list(@PathVariable String alias)
    {
        List list = listByClass(alias);
        if (list.isEmpty())
        {
            return new ResponseEntity<List>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/{alias}/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save(@PathVariable String alias, @RequestBody Object entity)
    {
        saveEntity(alias, entity);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
