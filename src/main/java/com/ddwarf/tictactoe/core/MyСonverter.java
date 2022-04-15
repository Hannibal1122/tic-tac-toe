
package com.ddwarf.tictactoe.core;
public class MyСonverter {

     public static String in16 (String str) {
         String out = "";
         int k = 0;
         int b = 0; // в эту переменную записываем десятичное число
         for (int i = 0; i < str.length(); i++) {
             b <<= 1;
             if (str.charAt(i) == '1') b++;
             if (k == 0) {
                 System.out.println(b);
                 out = out + Converter10to16.toChar(b);
                 b = 0;
             }
         }
         // 0 0000000 00000000 00000000 00000000
         // вариант 2
         /*int k = 0;
         for (int i = str.length() - 1; i >= 0; i--) {
             b = (int) (b + get2(str.charAt(i)) * Math.pow(2, k++));
         }*/
         System.out.println(b);
     //    return new StringBuilder(out).reverse().toString();
         return out;
     }
     public static int get2 (char c) {
         if (c == '1') return 1;
         return 0;
     }
}
