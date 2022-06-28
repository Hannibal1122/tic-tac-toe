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
        return 0;
        // условие вычисления выгодного для бота варианта хода
        
    }
    private boolean winning(int[] board, int player) {
        if (
            (board[0] == player && board[1] == player && board[2] == player) ||
            (board[3] == player && board[4] == player && board[5] == player) ||
            (board[6] == player && board[7] == player && board[8] == player) ||
            (board[0] == player && board[3] == player && board[6] == player) ||
            (board[1] == player && board[4] == player && board[7] == player) ||
            (board[2] == player && board[5] == player && board[8] == player) ||
            (board[0] == player && board[4] == player && board[8] == player) ||
            (board[2] == player && board[4] == player && board[6] == player)
        ) {
            return true;
        } else {
            return false;
        }
    }

}











/*
Идея 2
создать бота выставляющего нолик в строку рядом с предыдущим ходом противника

идея 3
создать бота проверяющего перед своим ходом где у противника больше всего в ряд и в последствии занимающего место на этой линии

идея 4
создать бота чередующего идею 3 и скоростное выставление ноликов в ряд
*/

//[0]        [1]        [2]
//[1][2][3]  [1][2][3]  [1][2][3]