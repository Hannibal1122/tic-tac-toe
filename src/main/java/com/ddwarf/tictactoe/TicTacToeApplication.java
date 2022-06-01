package com.ddwarf.tictactoe;

import com.ddwarf.tictactoe.core.TicTacToeEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Random;

@SpringBootApplication
public class TicTacToeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicTacToeApplication.class, args);

        TicTacToeEngine engine = new TicTacToeEngine();
        engine.generateField(10, 10);

        ArrayList<Position> possible = new ArrayList<>();
        for(int i = 0; i < 10; i++)
            for(int j = 0; j < 10; j++)
                possible.add(new Position(i, j));

        for(int i = 0; i < 100; i++)
        {
            Position position = TicTacToeApplication.click(possible);
            if(i % 2 == 0)
                engine.insertCrosse(position.i, position.j);
            else engine.insertZero(position.i, position.j);
            System.out.println(position.i + " " + position.j); //отсюда убрала engine.state
        }
    }
    public static Position click(ArrayList<Position> possible)
    {
        int r = new Random().nextInt(possible.size());
        Position position = possible.get(r);
        possible.remove(r);
        return position;
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