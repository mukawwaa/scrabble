package com.gamecity.scrabble.api.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CellRule
{
    private Integer cellNumber;
    private Integer rowNumber;
    private Integer columnNumber;
    private String color;
    private Integer letterMultiplier;
    private Integer wordMultiplier;
    private Boolean hasRight;
    private Boolean hasLeft;
    private Boolean hasTop;
    private Boolean hasBottom;
    private Boolean startingCell;
    private Rule rule;

    public Integer getCellNumber()
    {
        return cellNumber;
    }

    public void setCellNumber(Integer cellNumber)
    {
        this.cellNumber = cellNumber;
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

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public Integer getLetterMultiplier()
    {
        return letterMultiplier;
    }

    public void setLetterMultiplier(Integer letterMultiplier)
    {
        this.letterMultiplier = letterMultiplier;
    }

    public Integer getWordMultiplier()
    {
        return wordMultiplier;
    }

    public void setWordMultiplier(Integer wordMultiplier)
    {
        this.wordMultiplier = wordMultiplier;
    }

    public Boolean getHasRight()
    {
        return hasRight;
    }

    public void setHasRight(Boolean hasRight)
    {
        this.hasRight = hasRight;
    }

    public Boolean getHasLeft()
    {
        return hasLeft;
    }

    public void setHasLeft(Boolean hasLeft)
    {
        this.hasLeft = hasLeft;
    }

    public Boolean getHasTop()
    {
        return hasTop;
    }

    public void setHasTop(Boolean hasTop)
    {
        this.hasTop = hasTop;
    }

    public Boolean getHasBottom()
    {
        return hasBottom;
    }

    public void setHasBottom(Boolean hasBottom)
    {
        this.hasBottom = hasBottom;
    }

    public Rule getRule()
    {
        return rule;
    }

    public void setRule(Rule rule)
    {
        this.rule = rule;
    }

    public Boolean getStartingCell()
    {
        return startingCell;
    }

    public void setStartingCell(Boolean startingCell)
    {
        this.startingCell = startingCell;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
