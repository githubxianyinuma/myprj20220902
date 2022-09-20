package com.iwe;

import java.util.Random;

public class Demo2 {
    public static void main(String[] args) {
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        char[] c = s.toCharArray();
        Random random = new Random();
        String a = "";
        for( int i = 0; i < 8; i ++) {
            System.out.println(c[random.nextInt(c.length)]);
        }
    }
}
