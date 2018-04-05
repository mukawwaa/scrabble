package com.gamecity.scrabble.api.model;

public class Rule
{
    private Integer rowSize;
    private Integer columnSize;
    private Integer cellSize;
    private Integer rackSize;

    public Integer getRowSize()
    {
        return rowSize;
    }

    public void setRowSize(Integer rowSize)
    {
        this.rowSize = rowSize;
    }

    public Integer getColumnSize()
    {
        return columnSize;
    }

    public void setColumnSize(Integer columnSize)
    {
        this.columnSize = columnSize;
    }

    public Integer getCellSize()
    {
        return cellSize;
    }

    public void setCellSize(Integer cellSize)
    {
        this.cellSize = cellSize;
    }

    public Integer getRackSize()
    {
        return rackSize;
    }

    public void setRackSize(Integer rackSize)
    {
        this.rackSize = rackSize;
    }
}
