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
        MinMax();
        return null;
    }
    // метод функции минмакс
    public int MinMax()
    {
        //превод информации из двумерного массива в одномерный
        int[] newBoard = new int[field.length * field[0].length];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                newBoard[i * field[0].length + j] = field[i][j];
            }
        }
        return 0;
    }

}

//[0]        [1]        [2]
//[1][2][3]  [1][2][3]  [1][2][3]