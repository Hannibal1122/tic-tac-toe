package com.ddwarf.tictactoe.core;

public class Animal {
    public float weight;
    public int health = 10;
    public int damage;
    public int speed;
    public Animal(int damage, int speed) {
        this.damage = damage;
        this.speed = speed;
    }
    public void eat(int food) {
        health = health + food;
    }
    public void attack(Animal animal) {
        animal.health = animal.health - damage;
    }
    public void run() {
        health = health + speed;
    }
}
