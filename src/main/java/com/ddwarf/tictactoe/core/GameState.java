package com.ddwarf.tictactoe.core;

public enum GameState {
    GAME_BEGIN ("Игра начата"),
    CROSSES_WIN("Крестики победили"),
    ZEROS_WIN("Нолики победили"),
    WIN_WIN("Ничья");

    GameState(String s) {
    }

}