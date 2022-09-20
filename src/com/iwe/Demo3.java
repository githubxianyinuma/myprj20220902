package com.iwe;

import java.util.Random;

public class Demo3 {
    public static void main(String[] args) {
        Random random = new Random();
        String b = "";
        for( int i = 0; i < 4; i ++) {
            int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写还是小写
            b += choice;
            System.out.println((char)(choice + random.nextInt(26)));
        }
        return;
    }
}
