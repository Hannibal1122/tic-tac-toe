package com.ddwarf.tictactoe.core;

import java.sql.SQLOutput;

public class Converter10to16 {
    public static char[] I = {'A', 'B', 'C', 'D', 'E', 'F'};
    public static String to16 (int x) {
        int z;
        String y = "";
        while (x >= 16) {
            z = x % 16;
            y = y + Converter10to16.toChar(z);
            x = x/16;
        }
        y = y + Converter10to16.toChar(x);
        return new StringBuilder(y).reverse().toString();
    }
    private static String toChar (int x) {
        if (x < 10) {
            return String.valueOf(x);
        }
        return String.valueOf(Converter10to16.I[x - 10]);
    }
}
