package com.gamecity.scrabble.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gamecity.scrabble.Resources;
import com.gamecity.scrabble.Resources.Services;
import com.gamecity.scrabble.model.api.Board;
import com.gamecity.scrabble.model.api.BoardParams;
import com.gamecity.scrabble.model.api.Rack;

@RestController
@RequestMapping(value = "/board")
public class BoardController extends BaseController
{
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Board> create(@RequestBody BoardParams params)
    {
        params.setUserId(getUserId());
        params.setRuleId(getRuleId());
        Board board = post(Resources.BoardResource.create, Services.board.clazz, params);
        return new ResponseEntity<Board>(board, HttpStatus.OK);
    }

    @RequestMapping(value = "/{boardId}/join", method = RequestMethod.POST)
    @ResponseBody
    public void join(@PathVariable Long boardId)
    {
        post(Resources.BoardResource.join, null, null, boardId, getUserId());
    }

    @RequestMapping(value = "/{boardId}/leave", method = RequestMethod.POST)
    @ResponseBody
    public void leave(@PathVariable Long boardId)
    {
        post(Resources.BoardResource.leave, null, null, boardId, getUserId());
    }

    @RequestMapping(value = "/{boardId}/play", method = RequestMethod.POST)
    @ResponseBody
    public void play(@PathVariable Long boardId, @RequestBody Rack rack)
    {
        rack.setUserId(getUserId());
        post(Resources.GameResource.play, null, rack);
    }

    @RequestMapping(value = "/{boardId}/sendMessage", method = RequestMethod.POST)
    @ResponseBody
    public void sendMessage(@PathVariable Long boardId, @RequestBody String message)
    {
        post(Resources.ChatResource.send, null, message, boardId, getUserId());
    }

    @RequestMapping(value = "/{boardId}/rack", method = RequestMethod.GET)
    public ResponseEntity<Rack> getRack(@PathVariable Long boardId)
    {
        Rack rack = getByCriteria(Resources.ContentResource.rack, Rack.class, boardId, getUserId());
        return new ResponseEntity<Rack>(rack, HttpStatus.OK);
    }

    @RequestMapping(value = "/all/active", method = RequestMethod.GET)
    public ResponseEntity<List> getActiveBoards()
    {
        List<Board> activeBoards = listByCriteria(Resources.BoardResource.activeBoards, Board.class);
        return new ResponseEntity<List>(activeBoards, HttpStatus.OK);
    }

    @RequestMapping(value = "/my/active", method = RequestMethod.GET)
    public ResponseEntity<List> getMyBoards()
    {
        List<Board> myBoards = listByCriteria(Resources.BoardResource.myBoards, Board.class, getUserId());
        return new ResponseEntity<List>(myBoards, HttpStatus.OK);
    }
}
