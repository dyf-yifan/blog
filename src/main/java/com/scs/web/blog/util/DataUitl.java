package com.scs.web.blog.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDate;
import java.util.Random;

/**
 * @ClassName DataUitl
 * @Description 数据生成工具，用来生成用户的一些数据
 * @Author DataUtil
 * @Date 2019/11/9 13:55
 * @Version 1.0
 **/
public class DataUitl {
    /**
     * 获得电话号码
     * @return
     */
    public static  String getMobile(){
        StringBuilder stringBuilder = new StringBuilder("139");
        Random random = new Random();
        for( int i = 0; i < 8; i++){
            int num = random.nextInt(10);
            stringBuilder.append(num);
        }
        return stringBuilder.toString();
    }

    /**
     * 获得密码
     * @return
     */
    public static String getPassword(){
        StringBuilder password = new StringBuilder("");
        Random random = new Random();
        for (int i = 0; i<6; i++){
            int num = random.nextInt(10);
            password.append(num);
        }
        return DigestUtils.md5Hex(password.toString());
    }

    public static String getGender(){
        String [] genders = new String[]{"男","女"};
        Random random = new Random();
        int index = random.nextInt(2);
        return  genders[index];
    }

    public static LocalDate getBirthday() {
        LocalDate now = LocalDate.now();
        Random random = new Random();
        int bound = random.nextInt(8888);
        return now.minusDays(bound);
    }
    public static int getUserid(){
        Random random = new Random();
        int userid = random.nextInt(200);
        return userid;
    }
    public static int getDiamond(){
        Random random = new Random();
        int diamond = random.nextInt(100);
        return diamond;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++){
            System.out.println(getDiamond());
            System.out.println(getMobile());
        }
    }
}
