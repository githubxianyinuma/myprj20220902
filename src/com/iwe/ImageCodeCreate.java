package com.iwe;

import com.iwe.util.ImageCodeUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ImageCodeCreate {
    public static void main(String[] args) throws IOException {
        //生成一张宽度300，高度100，字符是ABCD的图片，图片在流中，存放在内存中，程序运行完成后，便已经消失
        //BufferedImage bufferedImage = ImageCodeUtils.createImageCode(300, 100, "ABCD");
        //生成一张宽度300，高度100，字符是getrandom的图片，图片在流中，存放在内存中
        BufferedImage bufferedImage = ImageCodeUtils.createImageCode(300, 100, getRandomStr(2));
        //从内存中，写入硬盘中
        //把bufferedImage以jpg的格式，写入imgcode文件夹，new  FileOutputStream新的文件输出流
        ImageIO.write(bufferedImage,"jpg",new  FileOutputStream("D:\\imgcode\\yzm.jpg"));
    }

    /**
     * 生成长度为len的随机字符串（包括数字和字符）
     *
     */
    public static String getRandomStr(int len) {
        //生成随机的字母
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        char[] c = s.toCharArray();
        Random random = new Random();
        String a = "";
        List<Object> list = null;
        for (int i = 0; i < len; i++) {
            char ranInt1 = c[random.nextInt(c.length)];
            int ranInt = random.nextInt(10);//每次能生成一个0-9的数字
            /*
             * https://blog.csdn.net/qq_36929361/article/details/84926277
             * 我们看一下是java.lang.Iterable<T>下的一个默认方法forEach调用的，
             * 好家伙，一看到这个function包下面的被@FunctionalInterface注解声明的Consumer<T>接口
             * 瞬间就了然了，这不又是函数式编程搞的鬼么
             * System.out::println这段代码其实就是Consumer<T>接口的一个实现方式啊。
             * 就是把你遍历出来的每一个对象都用来去调用System.out（也就是PrintStream类的一个实例）的println方法。
             */
            list = new ArrayList<>();
            list.add(ranInt1);
            list.add(ranInt);
            //System.out.println(list);
            //list.forEach(System.out::println);
//            list.forEach(x -> {
//                System.out.println(x);
//            });
            //Java中List集合循环遍历取值
            for (Object o : list) {
                //System.out.println(o);
                a += o;
            }
        }
        return a;
    }
}
