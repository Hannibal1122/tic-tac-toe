package com.ddwarf.tictactoe.core;

public class Animal {
    public float weight;
    public int health = 10;
    public void eat(int food) {
        health = health + food;
    }
}
