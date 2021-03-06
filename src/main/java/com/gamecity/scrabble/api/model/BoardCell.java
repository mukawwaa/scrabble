package com.gamecity.scrabble.api.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BoardCell
{
    private Long boardId;
    private CellRule rule;
    private String letter;
    private Integer score;
    private Boolean used;

    public Long getBoardId()
    {
        return boardId;
    }

    public void setBoardId(Long boardId)
    {
        this.boardId = boardId;
    }

    public CellRule getRule()
    {
        return rule;
    }

    public void setRule(CellRule rule)
    {
        this.rule = rule;
    }

    public String getLetter()
    {
        return letter;
    }

    public void setLetter(String letter)
    {
        this.letter = letter;
    }

    public Integer getScore()
    {
        return score;
    }

    public void setScore(Integer score)
    {
        this.score = score;
    }

    public Boolean getUsed()
    {
        return used;
    }

    public void setUsed(Boolean used)
    {
        this.used = used;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
