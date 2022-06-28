package com.ddwarf.tictactoe.core.ttt.ai;

import java.util.ArrayList;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ddwarf.tictactoe.core.ttt.FieldState;
import com.ddwarf.tictactoe.core.ttt.classes.ClickByFieldEmit;

public class RobbinRotten extends MainAI {

    Logger logger = LoggerFactory.getLogger("Tic-Tac-Toe");

    ArrayList<ClickByFieldEmit> possible = new ArrayList<>();
    public int[][] field;

    int condition = 5 - 1;

    public RobbinRotten(int[][] field, int fieldState) {

        this.field = field;

        for(int i = 0; i < field.length; i++)
            for(int j = 0; j < field[i].length; j++)
                possible.add(new ClickByFieldEmit(fieldState, i, j));
    }

    public ClickByFieldEmit getNextClick() {
        for (int i = 0; i < field.length; i++) 
        {
            for (int j = 0; j < field[0].length; j++) {
                if (field [i][j] == 1) {

                }
            }
        }
        int length = possible.size();

        if(length == 0) return null;

        int r = new Random().nextInt(length);

        ClickByFieldEmit position = possible.get(r);
        possible.remove(r);
        if(field[position.i][position.j] != FieldState.EMPTY) {
            return getNextClick();
        }
        return position;
    }
    public ClickByFieldEmit winning(int i, int j) {
        int sum = 0;
        sum = 1 + getSum(i, j, 0, 1, field[i][j]) + getSum(i, j, 0, -1, field[i][j]);
        if (sum == condition) return ;
        sum = 1 + getSum(i, j, 1, 0, field[i][j]) + getSum(i, j, -1, 0, field[i][j]);
        if (sum == condition) return true;
        sum = 1 + getSum(i, j, -1, -1, field[i][j]) + getSum(i, j, 1, 1, field[i][j]);
        if (sum == condition) return true;
        sum = 1 + getSum(i, j, -1, 1, field[i][j]) + getSum(i, j, 1, -1, field[i][j]);
        if (sum == condition) return true; 
        return null;
    }
    private int getSum(int i, int j, int dirI, int dirJ, int state) {
        int sum = 0;
        int newI = i + dirI;
        int newJ = j + dirJ;
        if (newJ < 0 || newI < 0 || newJ >= field[0].length || newI >= field.length)
            return sum;
        if (field[newI][newJ] == state) {
            sum = 1 + getSum(newI, newJ, dirI, dirJ, state);
        }
        return sum;
    }

}

