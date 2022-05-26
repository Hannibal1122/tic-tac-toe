package com.ddwarf.tictactoe.core;

public abstract class TestMassive {
    int[] neon;

    public void TestMassive(int x) {
        neon = new int[x];
        for (int i = 0; i < x; ++i) {
            neon[i] = i;
        }
    }
    public void svap(int h) {
        int b = neon[h];
        neon[h] = neon[h + 1];
        neon[h + 1] = b;
    }
}
