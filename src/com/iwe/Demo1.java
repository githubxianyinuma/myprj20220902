package com.iwe;

import java.util.Random;

public class Demo1 {
    public static void main(String[] args) {

        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            String b = "";
            int ran = random.nextInt(10);//生成一个0-20的随机数字，包括0，但是不包括20
            b += ran;
            System.out.println(ran);
        }
        return;
    }
}
