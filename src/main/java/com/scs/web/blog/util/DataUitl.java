package com.scs.web.blog.util;

import com.scs.web.blog.entity.Region;
import com.scs.web.blog.factory.DaoFactory;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
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
    public static  String getEmail(){
        StringBuilder stringBuilder = new StringBuilder("510");
        Random random = new Random();
        for( int i = 0; i < 7; i++){
            int num = random.nextInt(10);
            stringBuilder.append(num);
        }
        return stringBuilder.toString()+"@qq.com";
    }
    public static short getStatus(){
        Random random = new Random();
        int status = random.nextInt(3);
        return (short) status;
    }
    /**
     * 获得密码
     * @return
     */
    public static String getPassword(){
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++){
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
    /**
     * @return
     */
    public static Long getUserId() {
        Random random = new Random();
        long bound = random.nextInt(72);
        return bound;
    }

    public static int getDiamond(){
        Random random = new Random();
        int diamond = random.nextInt(100);
        return diamond;
    }
    public static short getDia(){
        Random random = new Random();
        short dia = (short) random.nextInt(100);
        return dia;
    }
    /**
     * 随机生成地址
     *
     * @return
     */
    public static String getAddress() {
        Random random = new Random();
        String address = null;
        try {
            List<Region> regionList = DaoFactory.getRegionDaoInstance().selectAll();
            Region region = regionList.get(random.nextInt(regionList.size()));
            address = region.getMergeName();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }
}
