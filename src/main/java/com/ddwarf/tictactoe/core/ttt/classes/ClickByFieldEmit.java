package com.ddwarf.tictactoe.core.ttt.classes;

public class ClickByFieldEmit {
    /** Тип ячейки (крестики / нолики) */
    public int fieldType;
    /** Позиция в строке */
    public int i;
    /** Позиция в столбце */
    public int j;
    /**  */
    public String state;
    public String queue;
    public ClickByFieldEmit(int fieldType, int i, int j)
    {
        this.fieldType = fieldType;
        this.i = i;
        this.j = j;
    }
    public ClickByFieldEmit(int fieldType, int i, int j, String state, String queue)
    {
        this.fieldType = fieldType;
        this.i = i;
        this.j = j;
        this.state = state;
        this.queue = queue;
    }
}