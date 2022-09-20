package com.iwe.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class YzmDemo {
    public static void main(String[] args) throws IOException {
        BufferedImage bufferedImage =ImageCodeUtils.createImageCode(300,100,getRandomStr(4));
        ImageIO.write(bufferedImage,"jpg",new FileOutputStream("D:\\imgcode\\yzm1.jpg"));
        //测试是否生成正确
//        for (int i = 0; i < 10; i++) {
//            String randomStr = YzmDemo.getRandomStr(4);
//            System.out.println(randomStr);
//        }
    }
    /**
     * 要生成一定长度的随机字符串AY7U
     */
    public static String getRandomStr(int len){
        String str = "3456789QWERTYUIOPASDFGHJKLMNBVCXZ";
        /**
         * string每生成新的字符串，将指向新的，旧的不删除，形成内存垃圾
         * stringbuffer没有垃圾产生
         */
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0;i < len;i++){
            int randomInt = random.nextInt(str.length());//随机生成字符串，最大为str长度减一
            //.var生成变量
            sb.append(str.charAt(randomInt));//取一个字符加入str里面
        }
        return sb.toString();//调用这个方法，将stringbuffer转换成string
    }
}
