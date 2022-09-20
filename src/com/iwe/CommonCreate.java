package com.iwe;

import com.iwe.util.CommonUtils;

public class CommonCreate {
    //入口函数，主函数
    public static void main(String[] args) {
        String MD5 = CommonUtils.calculateMD5("12345");
        System.out.println(MD5);
    }
}
