package com.ddwarf.tictactoe.core;

public class Massive {
    public static int[][] createMassive(int n, int m) { //n - строка, m - столбец
        int[][] matrix = new int[n][m];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                matrix[j][i] = i + 1;
            }
        }
        return matrix;
    }
    public static int[][] lineMassive(int a, int b, int[][] massive) {
        // a - какую копируем, b - куда переносим
        int[] array;
        if (a > b) {
            for (int i = a; b != i; i--) {
                array = massive[a];
                massive[a] = massive[a - 1];
                massive[a - 1] = array;
            }
        }
        else {
            for (int i = a; b != i; i++) {
                array = massive[a];
                massive[a] = massive[a + 1];
                massive[a + 1] = array;
            }
        }
        return massive;
    }

    public static int[][] columnMassive(int c, int d, int[][] massive) {
        //c - какой сдвигаем, d - куда сдвигаем
        int[] array = new int[massive.length];
        if (c > d) {
            for (int i = 0; (d != c) && (i != massive.length); c--) {
                array[i] = massive[i][c];
                massive[i][c] = massive[i][c - 1];
                massive[i][c - 1] = array[i];
                i++;
            }
        }
        else {
            for(int i = 0; (d != c) && (i != massive.length); c++) {
                array[i] = massive[i][c];
                massive[i][c] =massive[i][c + 1];
                massive[i][c + 1] = array[i];
                i++;
            }
        }
        return massive;
    }

}

