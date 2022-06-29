package com.ddwarf.tictactoe.core.ttt.ai;

import com.ddwarf.tictactoe.core.ttt.classes.ClickByFieldEmit;

public abstract class MainAI {

    public static String[] allAI = {
        "Lemon Johnny",
        "Altron",
        "PeterGriffin",
        "RobbinRotten"
        /** Сюда надо добавить имя вашего AI, чтобы он отобразился на фронте */
    };

    public static MainAI getAI(String name, int[][] field, int fieldState, int condition)
    {
        switch (name) {
            case "Lemon Johnny":
                return new RandomAI(field, fieldState);
            case "Altron":
                return new Altron(field, fieldState);
            case "PeterGriffin":
                return new PeterGriffin(field, fieldState);
            case "RobbinRotten":
                return new RobbinRotten(field, fieldState, condition);
            /** 
             * Здесь надо возвращать объект вашего AI
             * @example
             * case ["Имя вашего AI"]
             *  return new Ваш AI
             * */
        }

        return null;
    }

    public abstract ClickByFieldEmit getNextClick();
}
