package com.ddwarf.tictactoe.core;

import java.util.Scanner;

public class Сonverter {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String binaryNumber = console.nextLine();
        System.out.println("Двоичное число " + binaryNumber + " равно шестнадцатеричному числу " + toHex(binaryNumber));
        String hexNumber = console.nextLine();
        System.out.println("Шестнадцатеричное число " + hexNumber + " равно двоичному числу " + toBinary(hexNumber));
    }

    public static String toHex(String binaryNumber) {

        if ((binaryNumber == null) || (binaryNumber == "")){
            return "";
        }
        else if (binaryNumber ) { //HELP!!! нужно прописать условие: если в строке содержится любой символ кроме 0 и 1
            return "";
        }

        if (!(binaryNumber.length()%4 == 0)) {
            while (!(binaryNumber.length()%4 == 0)) {
                binaryNumber = "0" + binaryNumber;
            }
        }
        else {
            binaryNumber = binaryNumber.substring(4); //нужно разбить строку по 4 символа
        }

    }

    public static String toBinary(String hexNumber) {

        if ((hexNumber == null) || (hexNumber == "")) {
            return "";
        }
        else if ()

    }

}
