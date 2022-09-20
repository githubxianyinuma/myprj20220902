package com.iwe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Demo4 {
    public static void main(String[] args) {
        //将数字和字母存入一个数组
//        int a = 520;
//        String b = "xing";
//        List<Object> list = new ArrayList<>();
//        list.add(a);
//        list.add(b);
//        //list.forEach(System.out::println);
//        list.forEach(x -> {
//            System.out.println(x);
//        });

        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        char[] c = s.toCharArray();
        Random random = new Random();
        String a = "";
        List<Object> list = null;
        for (int i = 0; i < 2; i++) {
            char ranInt1 = c[random.nextInt(c.length)];
            int ranInt = random.nextInt(10);//每次能生成一个0-9的数字
            list = new ArrayList<>();
            list.add(ranInt1);
            list.add(ranInt);
            //list.forEach(System.out::println);
//            list.forEach(x -> {
//                System.out.println(x);
//            });
            for (Object o : list) {
                System.out.println(o);
                a += o;
            }
        }
        return;
    }
}
