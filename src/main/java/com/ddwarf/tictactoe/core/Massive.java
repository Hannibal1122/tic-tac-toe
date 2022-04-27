package com.ddwarf.tictactoe.core;

public class Massive {
    int[][] matrix;
    //n - строка, m - столбец
    public Massive(int n, int m)
    {
        matrix = new int[n][m];
        for (int j = 0; j < n; j++) {
            System.out.println("");
            for (int i = 0; i < m; i++) {
                matrix[j][i] = j * n + i;
            }
        }
    }
    public void lineMassive(int a, int b) {
        // a - какую копируем, b - куда переносим
        int[] array;
        if (a > b) {
            for (int i = a; i >= Math.max(1, b + 1); i--) {
                array = matrix[i];
                matrix[i] = matrix[i - 1];
                matrix[i - 1] = array;
            }
        }
        else {
            for (int i = a; i < Math.min(matrix.length - 1, b); i++) {
                array = matrix[i];
                matrix[i] = matrix[i + 1];
                matrix[i + 1] = array;
            }
        }
    }

    public void columnMassive(int c, int d) {
        //c - какой сдвигаем, d - куда сдвигаем
        int[] array = new int[matrix.length];
        if (c > d) {
            while(c > d)
            {
                // switchColumn(-1);
                for(int i = 0; i < matrix.length; i++) {
                    array[i] = matrix[i][c];
                    matrix[i][c] = matrix[i][c - 1];
                    matrix[i][c - 1] = array[i];
                }
                c--;
            }
        }
        else {
            while(c < d)
            {
                // switchColumn(1);
                for(int i = 0; i < matrix.length; i++) {
                    array[i] = matrix[i][c];
                    matrix[i][c] = matrix[i][c + 1];
                    matrix[i][c + 1] = array[i];
                }
                c++;
            }
        }
    }
    public void switchRow(int direction)
    {
        // any
    }
    public void switchColumn(int direction)
    {
        // any
    }
    public void print()
    {
        for (int j = 0; j < matrix.length; j++) {
            for (int i = 0; i < matrix[j].length; i++) {
                System.out.print(String.format("%1$3s", matrix[j][i]));
            }
            System.out.println();
        }
    }
}

