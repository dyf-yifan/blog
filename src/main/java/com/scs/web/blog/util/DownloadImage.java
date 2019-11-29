package com.scs.web.blog.util;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @ClassName DownloadImage
 * @Description TODO
 * @Author ding
 * @Date 2019/11/28 7:44
 * @Version 1.0
 **/
public class DownloadImage implements Runnable{
    String downUrl;
    public DownloadImage(String downUrl) {
        this.downUrl = downUrl;
    }
    @Override
    public void run() {
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        try {
            //生成url对象
            URL url = new URL(downUrl);
            //创建urlconnection对象
            URLConnection uc = url.openConnection();
            //获取uc的输入流
            bis = new BufferedInputStream(uc.getInputStream());
            //创建图片的存储对象
            String[] p = downUrl.split("/");
            String path = "D:\\6downloadtool\\"+p[p.length - 1];
            fos = new FileOutputStream(path);
            int c;
            while ((c = bis.read()) != -1) {
                fos.write(c);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println(e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            }catch (Exception e) {
                System.out.println("失败");
            }
        }

        }
    }
