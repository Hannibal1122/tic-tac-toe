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

        ClickByFieldEmit out;
        out = getPossibleClick(i, j, 0, 1, 0, -1);
        if(out != null) return out;

        out = getPossibleClick(i, j, -1, 0, 1, 0);
        if(out != null) return out;

        out = getPossibleClick(i, j, -1, 1, 1, -1);
        if(out != null) return out;

        out = getPossibleClick(i, j, -1, -1, 1, 1);
        if(out != null) return out;

        return null;
    }

    private ClickByFieldEmit getPossibleClick(int i, int j, int dirIA, int dirJA, int dirIB, int dirJB)
    {
        int sum = 0;
        SumResponse responseA = getSum(i, j, dirIA, dirJA, FieldState.CROSSES);
        SumResponse responseB = getSum(i, j, dirIB, dirJB, FieldState.CROSSES);
        sum = 1 + responseA.sum + responseB.sum;

        if (sum >= condition) {
            if(responseA.i != -1 && field[responseA.i][responseA.j] != FieldState.ZEROS) {
                return new ClickByFieldEmit(FieldState.ZEROS, responseA.i, responseA.j);
            }
            if(responseB.i != -1 && field[responseB.i][responseB.j] != FieldState.ZEROS) {
                return new ClickByFieldEmit(FieldState.ZEROS, responseB.i, responseB.j);
            }
        }
        return null;
    }

    private SumResponse getSum(int i, int j, int dirI, int dirJ, int state) {
        SumResponse response = new SumResponse();
        int newI = i + dirI;
        int newJ = j + dirJ;
        if (newJ < 0 || newI < 0 || newJ >= field[0].length || newI >= field.length)
            return response;
        response.i = newI;
        response.j = newJ;
        if (field[newI][newJ] == state) {
            SumResponse reqResponse = getSum(newI, newJ, dirI, dirJ, state);
            response.sum = 1 + reqResponse.sum;
            response.i = reqResponse.i;
            response.j = reqResponse.j;
        }
        return response;
    }

}

class SumResponse {
    public int sum = 0;
    public int i = -1;
    public int j = -1;
}