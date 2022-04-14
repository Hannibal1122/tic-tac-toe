package com.ddwarf.tictactoe.core;

import java.sql.SQLOutput;

public class Converter10to16 {
    public static char[] I = {'A', 'B', 'C', 'D', 'E', 'F'};
    public static String to16 (int x, int n) {
        int z;
        String y = "";
        while (x >= n) {
            z = x % n;
            y = y + Converter10to16.toChar(z);
            x = x/n;
        }
        y = y + Converter10to16.toChar(x);
        return new StringBuilder(y).reverse().toString();
    }
    // код не будет работать с 2 и 8-ричными системами из-за метода toChar
    private static String toChar (int x) {
        if (x < 10) {
            return String.valueOf(x);
        }
        return String.valueOf(Converter10to16.I[x - 10]);
    }
}
