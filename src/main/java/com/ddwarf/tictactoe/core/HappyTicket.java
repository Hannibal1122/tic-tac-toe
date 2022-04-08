package com.ddwarf.tictactoe.core;

public class HappyTicket {

    public int generate(int number) {
        for (int i = number + 1; i < 1000000; i++) {
            if (checkSum(i))
                return i;
        }
        return number;
    }

    private boolean checkSum(int number) {
        int i = 0, a, sum = 0;
        while (number != 0) {
            a = number % 10;
            if (i < 3) {
                sum = sum + a;
            }
            else {
                sum = sum - a;
            }
            number = number / 10;
            i++;
        }
        return sum == 0;
    }
}
