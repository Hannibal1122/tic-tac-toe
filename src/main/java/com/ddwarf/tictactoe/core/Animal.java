package com.ddwarf.tictactoe.core;

import java.util.Random;

public class Animal {
    public boolean isDeath = false;
    public float weight;
    public int health = 10;
    public int damage;
    public int speed;
    private Random randomAttack = new Random();

    public Animal(int damage, int speed) {
        this.damage = damage;
        this.speed = speed;
    }
    public void eat(int food) {
        health = health + food;
    }
    public void attack(Animal animal) {
        int d = randomAttack.nextInt(damage) + 1;
        if (animal.isDeath) {
            return;
        }
        if (randomAttack.nextInt(10) > 3) {
            animal.health = animal.health - d;
            System.out.println("Animal был отакован на " + d);
            if (animal.health <= 0) {
                System.out.println("wasted");
                animal.isDeath = true;
                animal.health = 0;
            }
        }
        else {
            System.out.println("miss");
        }
    }
    public void run() {
        health = health + speed;
    }
}
