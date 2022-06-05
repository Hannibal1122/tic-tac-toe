package com.ddwarf.tictactoe.core.ttt;

// TODO спроектировать движок игры в крестики-нолики +
// TODO добавить метод генерации поля +
// TODO добавить метод вставки крестика или нолика на поле +
// TODO добавить метод рассчёта победы крестиков или ноликов +
public class TicTacToeEngine {

    public int[][] field;
    public String state = GameState.GAME_BEGIN;
    int condition = 7;
    public void generateField (int n, int m)
    {
        state = GameState.GAME_BEGIN;
        field = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                field[i][j] = FieldState.EMPTY;
            }
        }
    }

    public void insertCrosse(int i, int j)
    {
        field[i][j] = FieldState.CROSSES;
        this.state = winning(i, j);
    }

    public void insertZero(int i, int j)
    {
        field[i][j] = FieldState.ZEROS;
        this.state = winning(i, j);
    }

    public String winning(int i, int j) {
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
    public String setState(int state) {
        if (state == FieldState.CROSSES) return GameState.CROSSES_WIN;
        if (state == FieldState.ZEROS) return GameState.ZEROS_WIN;
        return this.state;
    }
// функция getSum задаёт направление + собирает значения, пока не встретит отличительный знак
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