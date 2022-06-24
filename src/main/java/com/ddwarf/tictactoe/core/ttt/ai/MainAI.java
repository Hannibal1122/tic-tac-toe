package com.ddwarf.tictactoe.core.ttt.ai;

import com.ddwarf.tictactoe.core.ttt.classes.ClickByFieldEmit;

public abstract class MainAI {

    public static String[] allAI = {
        "Lemon Johnny",
        /** Сюда надо добавить имя вашего AI, чтобы он отобразился на фронте */
    };

    public static MainAI getAI(String type, int[][] field, int fieldState)
    {
        switch (type) {
            case "Lemon Johnny":
                return new RandomAI(field, fieldState);
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
