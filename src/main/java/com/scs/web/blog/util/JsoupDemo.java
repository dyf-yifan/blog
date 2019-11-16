package com.scs.web.blog.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
/**
 * @ClassName JsoupDemo
 * @Description TODO
 * @Author
 * @Date 2019/11/10 7:34
 * @Version 1.0
 **/
public class JsoupDemo {
    public static void main(String[] args) throws Exception{
        //声明文档变量
        Document document;
        //通过JSoup连接目标界面
        document = Jsoup.connect("https://read.douban.com/charts").get();
        //选取所有class为main review-item的元素集合
        Elements divs = document.getElementsByClass("SpecialListCard SpecialListPage-specialCrad");
        //对divs遍历
        System.out.println(divs.html());
        divs.forEach(div->{

        });



    }
}
