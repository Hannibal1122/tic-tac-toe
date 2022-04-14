
package com.ddwarf.tictactoe.core;
public class Сonverter {

     public static String in16 (String x) {
         int b = 0; // в эту переменную записываем десятичное число
         /* вариант 1
         for (int i = 0; i < x.length(); i++) {
             b <<= 1;
             if (x.charAt(i) == '1') b++;
         } */
         // вариант 2
         for (int i = 0; i < x.length(); i++) { //когда i меньше длины двоичного числа
             b = (int) (b + x.charAt(i) * Math.pow(2, i)); /*в десятичное записываем
             предыдущее b плюс i-тая цифра в двоичном числе умноженная на 2 в степени i
             (int) - обозначает преобразование типа String к типу int */
         }
         return Converter10to16.to16(b);
     }
}
