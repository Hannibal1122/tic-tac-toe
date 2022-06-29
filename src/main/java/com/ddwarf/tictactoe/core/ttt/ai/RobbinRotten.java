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

    int condition = 5 - 2;

    public RobbinRotten(int[][] field, int fieldState) {

        this.field = field;

        for(int i = 0; i < field.length; i++)
            for(int j = 0; j < field[i].length; j++)
                possible.add(new ClickByFieldEmit(fieldState, i, j));
    }

    public ClickByFieldEmit getNextClick() {

        int length = possible.size();
        if(length == 0) return null;

        ClickByFieldEmit position = null;
        for (int i = 0; i < field.length; i++) 
        {
            int j = 0;
            for (; j < field[0].length; j++) {
                if (field[i][j] == 1) {
                    position = winning(i, j);
                    if(position != null) break;
                }
            }
            if(j != field[0].length) break;
        }

        if(position != null) return position;

        int r = new Random().nextInt(length);

        position = possible.get(r);
        possible.remove(r);
        if(field[position.i][position.j] != FieldState.EMPTY) {
            return getNextClick();
        }
        return position;
    }
    public ClickByFieldEmit winning(int i, int j) {
        int sum = 0;
        /*
        sum = 1 + getSum(i, j, 0, 1, field[i][j]) + getSum(i, j, 0, -1, field[i][j]);
        if (sum == condition) return setState(field[i][j]);
        */
        int rightSum = getSum(i, j, 0, 1, FieldState.CROSSES);
        int leftSum = getSum(i, j, 0, -1, FieldState.CROSSES);
        sum = 1 + leftSum + rightSum;
        int positionI = i;
        int positionJ = j - leftSum - 1;
        // logger.info("ClickByFieldEmit: " + sum + " " + i + " " + j + " leftSum: " + leftSum + " rightSum: " + rightSum);
        if (sum >= condition) return positionJ >= 0 && field[positionI][positionJ] == FieldState.EMPTY ?
            new ClickByFieldEmit(FieldState.ZEROS, positionI, positionJ) :
            new ClickByFieldEmit(FieldState.ZEROS, positionI, j + rightSum + 1);

        int upSum = getSum(i, j, -1, 0, FieldState.CROSSES);
        int downSum = getSum(i, j, 1, 0, FieldState.CROSSES);
        sum = 1 + upSum + downSum;
        positionI = i - upSum - 1;
        positionJ = j;
        // logger.info("ClickByFieldEmit: " + sum + " " + i + " " + j + " leftSum: " + leftSum + " rightSum: " + rightSum);
        if (sum >= condition) return positionI >= 0 && field[positionI][positionJ] == FieldState.EMPTY ?
            new ClickByFieldEmit(FieldState.ZEROS, positionI, positionJ) :
            new ClickByFieldEmit(FieldState.ZEROS, i + downSum + 1, positionJ);
        /* sum = 1 + getSum(i, j, 1, 0, field[i][j]) + getSum(i, j, -1, 0, field[i][j]);
        if (sum == condition) return true;*/
        int rightUpSum = getSum(i, j, -1, 1, FieldState.CROSSES);
        int leftDownSum = getSum(i, j, 1, -1, FieldState.CROSSES);
        sum = 1 + rightUpSum + leftDownSum;
        positionI = (i - rightUpSum - 1) + j + 1;
        positionJ = (i - leftDownSum + 1) + j - 1;
        if (sum >= condition) return positionJ >= 0 && field[positionI][positionJ] == FieldState.EMPTY ?
            new ClickByFieldEmit(FieldState.ZEROS, positionI, positionJ) :
            new ClickByFieldEmit(FieldState.ZEROS, i + leftDownSum + 1, j + rightUpSum + 1);
        /* sum = 1 + getSum(i, j, -1, -1, field[i][j]) + getSum(i, j, 1, 1, field[i][j]);
        if (sum == condition) return true; */
        int leftUpSum = getSum(i, j, -1, -1, FieldState.CROSSES);
        int rightDownSum = getSum(i, j, 1, 1, FieldState.CROSSES);
        sum = 1 + leftUpSum + rightDownSum;
        positionI = (i - leftUpSum - 1) + j - 1;
        positionJ = (i - rightDownSum + 1) + j + 1;
        if (sum >= condition) return positionJ >= 0 && field[positionI][positionJ] == FieldState.EMPTY ?
            new ClickByFieldEmit(FieldState.ZEROS, positionI, positionJ) :
            new ClickByFieldEmit(FieldState.ZEROS, i + rightDownSum + 1, j + leftUpSum + 1);
        /* sum = 1 + getSum(i, j, -1, 1, field[i][j]) + getSum(i, j, 1, -1, field[i][j]);
        if (sum == condition) return true;  */
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
