package com.ddwarf.tictactoe.core;

public class NumberG {

    public boolean a() {
        for (long b = 2; b <= 2147000000 ; b++) {
            while (b != 1) {
                if (b % 2 == 0) {
                    b = b / 2;
                } else {
                    b = 3 * b + 1;
                }
            }
        }
        return true;
    }
}
