package com.ddwarf.tictactoe.core;

public class Massive {
    int[][] matrix;

    //n - строка, m - столбец
    public Massive(int n, int m) {
        matrix = new int[n][m];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                matrix[j][i] = j * n + i;
            }
        }
    }
    public void lineMassive(int a, int b) {
        // a - какую копируем, b - куда переносим
        if (a > b) {
            for (int i = a; i >= Math.max(1, b + 1); i--) {
                switchRow(i,-1);
            }
        }
        else {
            for (int i = a; i < Math.min(matrix.length - 1, b); i++) {
                switchRow(i,1);
            }
        }
    }
/** Сдвигаем столбец с на столбец d.
 * с - это индекс столбца, который сдвигаем
 * d - это индекс столбца, куда сдвигаем
 * */
    public void columnMassive(int columnIndex, int columnShiftIndex) {
         if (columnIndex > columnShiftIndex) {
            while(columnIndex > columnShiftIndex)
            {
                switchColumn(columnIndex,-1);
                columnIndex--;
            }
        }
        else {
            while(columnIndex < columnShiftIndex)
            {
                switchColumn(columnIndex,1);
                columnIndex++;
            }
        }
    }
    public void switchRow(int rowIndex, int direction)
    {
        int[] array;
        array = matrix[rowIndex];
        matrix[rowIndex] = matrix[rowIndex + direction];
        matrix[rowIndex + direction] = array;
    }
    /**  */
    public void switchColumn(int columnIndex, int direction)
    {
        int[] array = new int[matrix.length];
        for(int i = 0; i < matrix.length; i++) {
            array[i] = matrix[i][columnIndex];
            matrix[i][columnIndex] = matrix[i][columnIndex + direction];
            matrix[i][columnIndex + direction] = array[i];
        }
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

