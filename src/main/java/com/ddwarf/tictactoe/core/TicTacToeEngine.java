package com.ddwarf.tictactoe.core;

// TODO спроектировать движок игры в крестики-нолики
// TODO добавить метод генерации поля
// TODO добавить метод вставки крестика или нолика на поле
// TODO добавить метод рассчёта победы крестиков или ноликов
public class TicTacToeEngine {

    FieldState[][] field;
    public GameState state = GameState.GAME_BEGIN;
    int condition = 5;
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

    public void insertCrosse(int i, int j)
    {
        field[i][j] = FieldState.CROSSES;
        System.out.println(winning(i, j)); //здесь добавила
    }

    public void insertZero(int i, int j)
    {
        field[i][j] = FieldState.ZEROS;
        System.out.println(winning(i, j)); //и здесь добавила
    }

    public GameState winning(int i, int j) {
        int sum = 0;
        sum = 1 + getSum(i, j, 0, 1, field[i][j]) + getSum(i, j, 0, -1, field[i][j]);
        if (sum == condition) return setState(field[i][j]);
        sum = 1 + getSum(i, j, 1, 0, field[i][j]) + getSum(i, j, -1, 0, field[i][j]);
        if (sum == condition) return setState(field[i][j]);
        sum = 1 + getSum(i, j, -1, -1, field[i][j]) + getSum(i, j, 1, 1, field[i][j]);
        if (sum == condition) return setState(field[i][j]);
        sum = 1 + getSum(i, j, -1, 1, field[i][j]) + getSum(i, j, 1, -1, field[i][j]);
        if (sum == condition) return setState(field[i][j]);
        return state;
    }
    public GameState setState(FieldState state) {
        if (state == FieldState.CROSSES) return GameState.CROSSES_WIN;
        if (state == FieldState.ZEROS) return GameState.ZEROS_WIN;
        return this.state;
    }
// функция getSum задвёт направление + собирает значения, пока не встретит отличительный знак
    private int getSum(int i, int j, int dirI, int dirJ, FieldState state) {
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
enum FieldState {
    EMPTY(0),
    CROSSES(1),
    ZEROS(2);

    FieldState(int i) {
    }
}

enum GameState {
    GAME_BEGIN ("Игра начата"),
    CROSSES_WIN("Крестики победили"),
    ZEROS_WIN("Нолики победили"),
    WIN_WIN("Ничья");

    GameState(String s) {
    }

}


