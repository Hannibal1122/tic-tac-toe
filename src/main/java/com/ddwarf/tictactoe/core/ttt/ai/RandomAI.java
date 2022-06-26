package com.ddwarf.tictactoe.core.ttt.ai;

import java.util.ArrayList;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ddwarf.tictactoe.core.ttt.FieldState;
import com.ddwarf.tictactoe.core.ttt.classes.ClickByFieldEmit;

public class RandomAI extends MainAI {

    Logger logger = LoggerFactory.getLogger("Tic-Tac-Toe");

    ArrayList<ClickByFieldEmit> possible = new ArrayList<>();
    public int[][] field;

    public RandomAI(int[][] field, int fieldState) {

        this.field = field;

        for(int i = 0; i < field.length; i++)
            for(int j = 0; j < field[i].length; j++)
                possible.add(new ClickByFieldEmit(fieldState, i, j));
    }

    public ClickByFieldEmit getNextClick() {
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

}
