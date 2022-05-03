package com.ddwarf.tictactoe.core;

// TODO спроектировать движок игры в крестики-нолики
// TODO добавить метод генерации поля
// TODO добавить метод вставки крестика или нолика на поле
// TODO добавить метод рассчёта победы крестиков или ноликов
public class TicTacToeEngine {

    FieldState[][] field;
    GameState state = GameState.GAME_BEGIN;

    public void generateField (int n, int m)
    {
        state = GameState.GAME_BEGIN;
        field = new FieldState[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                field[i][j] = FieldState.EMPTY;
            }
        }
    }
}
enum FieldState {
    EMPTY(0),
    CROSSES(1),
    ZEROS(2);

    FieldState(int i) {
    }
}
enum GameState {
    GAME_BEGIN("Игра начата"),
    CROSSES_WIN("Крестики победили"),
    ZEROS_WIN("Нолики победили");

    GameState(String s) {
    }
}
