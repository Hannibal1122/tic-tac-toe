package com.ddwarf.tictactoe;

import com.ddwarf.tictactoe.core.GameState;
import com.ddwarf.tictactoe.core.TicTacToeEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Random;

@SpringBootApplication
public class TicTacToeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicTacToeApplication.class, args);
    }
}