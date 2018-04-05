package com.gamecity.scrabble.api.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class RackTile implements Serializable
{
    private static final long serialVersionUID = 7105573134531054756L;
    private Integer tileNumber;
    private Integer rowNumber;
    private Integer columnNumber;
    private String letter;
    private Integer score;
    private Boolean used;

    public Integer getTileNumber()
    {
        return tileNumber;
    }

    public void setTileNumber(Integer tileNumber)
    {
        this.tileNumber = tileNumber;
    }

    public Integer getRowNumber()
    {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber)
    {
        this.rowNumber = rowNumber;
    }

    public Integer getColumnNumber()
    {
        return columnNumber;
    }

    public void setColumnNumber(Integer columnNumber)
    {
        this.columnNumber = columnNumber;
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
