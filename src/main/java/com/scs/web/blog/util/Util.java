package com.scs.web.blog.util;

import java.util.Random;

/**
 * @ClassName Util
 * @Description TODO
 * @Author ding
 * @Date 2019/11/20 10:30
 * @Version 1.0
 **/
public class Util {
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer y =new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            y.append(str.charAt(number));
        }
        return y.toString();
    }
//    final static int MAX = 4;
//    public static String getRandomString(){
//        StringBuilder stringBuilder = new StringBuilder();
//        Random random = new Random();
//        int digitalBound = 10;
//        int charBound = 26;
//        int index;
//        char c;
//        for (int i = 0; i < MAX; i++){
//            index = random.nextInt(digitalBound);
//            c = (char)('0' + index);
//            stringBuilder.append(c);
//        }
//        return stringBuilder.toString();
//    }
//
//    public static void main(String[] args) {
//        System.out.println(Util.getRandomString());
//    }
}
