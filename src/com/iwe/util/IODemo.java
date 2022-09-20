package com.iwe.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * IODemo这个类，要依赖于commons-io-2.6.jar这个包
 */
public class IODemo {
    public static void main(String[] args) throws IOException {
        //读取test.txt的内容，以UTF-8的编码格式
        List<String> strings = FileUtils.readLines(new File("D:\\imgcode\\test.txt"),"UTF-8");
        for (String s : strings)
        {
            System.out.println(s);
        }//增强for循环，这里将list集合每次的值存入S并输出
    }
}
