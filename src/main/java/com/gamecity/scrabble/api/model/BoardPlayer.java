package com.gamecity.scrabble.api.model;

import java.util.List;

public class BoardPlayer
{
    private Long boardId;
    private Integer orderNo;
    private Long currentUserId;
    private String currentUsername;
    private List<Player> players;

    public BoardPlayer()
    {
        super();
    }

    public Long getBoardId()
    {
        return boardId;
    }

    public void setBoardId(Long boardId)
    {
        this.boardId = boardId;
    }

    public Integer getOrderNo()
    {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo)
    {
        this.orderNo = orderNo;
    }

    public Long getCurrentUserId()
    {
        return currentUserId;
    }

    public void setCurrentUserId(Long currentUserId)
    {
        this.currentUserId = currentUserId;
    }

    public String getCurrentUsername()
    {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername)
    {
        this.currentUsername = currentUsername;
    }

    public List<Player> getPlayers()
    {
        return players;
    }

    public void setPlayers(List<Player> players)
    {
        this.players = players;
    }

}
