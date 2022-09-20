package com.iwe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Demo5 {

    public static void main(String[] args) {
        int length = 5;
        HashMap[] map = new HashMap[length];
        //向数组添加两个数，一个是String，一个是int
        boolean setValue = setValue(map, 0, 1);
        if (setValue) {
            System.out.println("添加成功！");
        }
        boolean setValue1 = setValue(map, 1, "a");
        if (setValue1) {
            System.out.println("添加成功！");
        }
        //取出数组中第index位的值
        int index = 1;
        Iterator it = map[index].keySet().iterator();
        Object key;
        while (it.hasNext()) {
            key = it.next();
            if (key.equals("String")) {
                //StrValue为得到的String值
                String StrValue = (String) map[index].get(key);
                System.out.println(key + ":" + StrValue);
            } else {
                //intValue为得到的int值
                int intValue = (int) map[index].get(key);
                System.out.println(key + ":" + intValue);
            }
        }
    }

    /**
     *@param  index 向第几位添加
     *@param  value 添加的内容
     * */
    public static boolean setValue(HashMap[] map, int index, String value) {
        if (index > map.length)
            return false;
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("String", value);
        map[index] = hashMap;
        return true;
    }

    /**
     *@param  index 向第几位添加
     *@param  value 添加的内容
     * */
    public static boolean setValue(HashMap[] map, int index, int value) {
        if (index > map.length)
            return false;
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("Integer", value);
        map[index] = hashMap;
        return true;
    }
}