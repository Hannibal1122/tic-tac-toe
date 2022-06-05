package com.ddwarf.tictactoe.core;

public class TestMassive extends MassiveAbstract {
    public void swap(int h) {
        int b = neon[h];
        neon[h] = neon[h + 1];
        neon[h + 1] = b;
    }
}
interface MassiveInterface
{
    public void TestMassive(int x);
    public void swap(int h);
}

abstract class MassiveAbstract implements MassiveInterface
{
    int[] neon;

    public void TestMassive(int x)
    {
        neon = new int[x];
        for (int i = 0; i < x; ++i) {
            neon[i] = i;
        }
    }
}