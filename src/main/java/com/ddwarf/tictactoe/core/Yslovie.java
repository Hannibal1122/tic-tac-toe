package com.ddwarf.tictactoe.core;

import java.util.ArrayList;
import java.util.Random;

public class Yslovie {
    public void StartTest() {
        int c = 0; int z = 0; int ww = 0;
        for(int i = 0; i < 100000; i++) {
            GameState state = this.StartGame();
            if (state == GameState.GAME_BEGIN) ww++;
            else if (state == GameState.CROSSES_WIN) c = c + 1;
            else z = z + 1;
            System.out.println(c + " " + z + " отдельно " + ww);
        }
    }
    public Position click(ArrayList<Position> possible) {
        int r = new Random().nextInt(possible.size());
        Position position = possible.get(r);
        possible.remove(r);
        return position;
    }
    public GameState StartGame() {
        TicTacToeEngine engine = new TicTacToeEngine();
        engine.generateField(10, 10);

        ArrayList<Position> possible = new ArrayList<>();
        for(int i = 0; i < 10; i++)
            for(int j = 0; j < 10; j++)
                possible.add(new Position(i, j));

        for(int i = 0; i < 100; i++)
        {
            if(engine.state != GameState.GAME_BEGIN) break;
            Position position = this.click(possible);
            if(i % 2 == 0)
                engine.insertCrosse(position.i, position.j);
            else engine.insertZero(position.i, position.j);
            // System.out.println(position.i + " " + position.j + " " + engine.state);
        }
        return engine.state;
    }
}

class Position
{
    int i;
    int j;
    public Position(int i, int j)
    {
        this.i = i;
        this.j = j;
    }
}
