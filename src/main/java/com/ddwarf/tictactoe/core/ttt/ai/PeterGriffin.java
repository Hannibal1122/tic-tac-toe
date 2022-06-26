package com.ddwarf.tictactoe.core.ttt.ai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ddwarf.tictactoe.core.ttt.FieldState;
import com.ddwarf.tictactoe.core.ttt.classes.ClickByFieldEmit;

public class PeterGriffin extends MainAI {
    Logger logger = LoggerFactory.getLogger("Tic-Tac-Toe");

    public int[][] field;

    public PeterGriffin(int[][] field, int fieldState) {

        this.field = field;
    }

    @Override
    public ClickByFieldEmit getNextClick() {
        // TODO Auto-generated method stub
        return null;
    }

}