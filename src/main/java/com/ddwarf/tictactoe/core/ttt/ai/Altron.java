package com.ddwarf.tictactoe.core.ttt.ai;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ddwarf.tictactoe.core.ttt.classes.ClickByFieldEmit;


// объявлен класс Altron
public class Altron extends MainAI {
    Logger logger = LoggerFactory.getLogger("Tic-Tac-Toe");

// объявлен двумерный массив поля
    public int[][] field;

    public Altron(int[][] field, int fieldState) {

        this.field = field;
    }
    public ClickByFieldEmit getNextClick() {
        MinMax();
        return null;
    }
    // метод функции минмакс
    public int MinMax() {
    // превод информации из двумерного массива в одномерный
        int[] newBoard = new int[field.length * field[0].length];
        for (int i = 0; i < field.length; i++) 
        {
            for (int j = 0; j < field[0].length; j++) {
                newBoard[i * field[0].length + j] = field[i][j];
            }
        }
        // объявление переменных
        int fc = 0;
        String hu = "o";
        String ai = "x";
        // подвод статистики лучшего варианта для ai
        int bestSpot = minimax(newBoard, ai);
        // регистрация результатов
           /*Log.v("index: " + bestSpot);
             Log.v("function calls: " + fc);*/
        return 0;
        // условие вычисления выгодного для бота варианта хода

    }
    private int minimax(int[] newBoard, String ai) {
        return 0;
    }

}

//[0]        [1]        [2]
//[1][2][3]  [1][2][3]  [1][2][3]