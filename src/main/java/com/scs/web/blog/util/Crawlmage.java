package com.scs.web.blog.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName Crawlmage
 * @Description TODO
 * @Author ding
 * @Date 2019/11/28 7:42
 * @Version 1.0
 **/
public class Crawlmage {
    public static void getUrl(Document document, ExecutorService pool) {
        Element id = document.getElementById("listBox");
        Elements els = id.getElementsByTag("img");
        for(Element el : els){
            String imageUrl = el.attr("src");
            pool.execute(new DownloadImage(imageUrl));
            System.out.println(el.attr("src"));
        }
    }

    public static void main(String[] args) throws Exception{
        try {
            //创建一个缓冲池
            ExecutorService pool = Executors.newCachedThreadPool();
            //设置其容量为九
            pool = Executors.newFixedThreadPool(9);
            //获取指定网页源码
            Document document = Jsoup.connect("http://item.kongfz.com/Cjisuanji/w2/").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31").get();
            getUrl(document,pool);
            int a = 4;
            while (a--!=0){
                Element el = document.getElementById("pageBox");
                Elements el2 = el.getElementsByClass("next-btn");
                if (el2 == null){
                    System.out.println("到最后了");
                    break;
                }
                String urlIndex = el2.attr("href");
                Document document1 = Jsoup.connect(urlIndex).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31").get();
                getUrl(document1,pool);
            }
            pool.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
