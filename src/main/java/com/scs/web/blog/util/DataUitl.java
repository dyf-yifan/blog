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
    public static String base = "abcdefghijklmnopqrstuvwxyz0123456789";
    public static final String[] email_suffix="@mail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn".split(",");
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
     *
     * @param start
     * @param end
     * @return
     */
    public static int getNum(int start,int end){
        return (int)(Math.random() * (end - start + 1) + start);
    }
    /**
     * 随机生成邮箱
     * @return
     */
    public static String getEmail(int lMin, int lMax) {
        int length = getNum(lMin,lMax);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++){
            int number = (int) (Math.random() * base.length());
            sb.append(base.charAt(number));
        }
        sb.append(email_suffix[(int) (Math.random() * email_suffix.length)]);
        return sb.toString();
    }

    public static short getStatus(){
        Random random = new Random();
        int status = random.nextInt(2);
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
        int max=72;
        int min=1;
        Random random = new Random();
        long bound = random.nextInt(max)%(max-min+1) + min;
        return bound;
    }

    public static void main(String[] args) {
        System.out.println(DataUitl.getUserId());
    }
    public static Long getThemeId() {
        int max=10;
        int min=1;
        Random random = new Random();
        long bound = random.nextInt(max)%(max-min+1) + min;
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
