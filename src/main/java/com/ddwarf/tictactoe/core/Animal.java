package com.ddwarf.tictactoe.core;

public class Animal {
    public float weight;
    public int health = 10;
    public int damage;
    public Animal(int damage) {
        this.damage = damage;
    }
    public void eat(int food) {
        health = health + food;
    }
    public void attack(Animal animal) {
        animal.health = animal.health - damage;
    }
}
