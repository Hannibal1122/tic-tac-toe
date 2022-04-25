package com.ddwarf.tictactoe.core;

public class Massive {
    public static int[][] createMassive(int n, int m) {
        int[][] matrix = new int[n][m];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = j + 1;
            }
        }
        return matrix;
    }

}

