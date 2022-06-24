package com.ddwarf.tictactoe.core.ttt.ai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ddwarf.tictactoe.core.ttt.classes.ClickByFieldEmit;

public class Altron extends MainAI {
    Logger logger = LoggerFactory.getLogger("Tic-Tac-Toe");

    public int[][] field;

    public Altron(int[][] field, int fieldState) {

        this.field = field;
    }
    public ClickByFieldEmit getNextClick() {
        // TODO Auto-generated method stub
        return null;
    }

}