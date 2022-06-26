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
    public ClickByFieldEmit(int fieldType, int i, int j, String state)
    {
        this.fieldType = fieldType;
        this.i = i;
        this.j = j;
        this.state = state;
    }
}