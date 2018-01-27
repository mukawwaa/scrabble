package com.gamecity.scrabble.model;

public class NotificationKey
{
    private Long boardId;
    private Integer orderNo;

    public NotificationKey(Long boardId, Integer orderNo)
    {
        this.boardId = boardId;
        this.orderNo = orderNo;
    }

    public Long getBoardId()
    {
        return boardId;
    }

    public Integer getOrderNo()
    {
        return orderNo;
    }
}
