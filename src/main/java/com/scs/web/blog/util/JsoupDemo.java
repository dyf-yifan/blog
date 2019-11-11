package com.scs.web.blog.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
        document = Jsoup.connect("https://book.douban.com/review/best/").get();
        //选取所有class为main review-item的元素集合
        Elements divs = document.getElementsByClass("main review-item");
        //对divs遍历
        divs.forEach(div->{
            Element on = div.child(0);
            Element cover = on.child(0);
            Element one = div.child(1);
            Element name = one.child(1);
            Element diamond = one.child(2);
            Element time;
                if(one.childNodeSize() == 4){
                     time = one.child(3);
                }else{
                    time = one.child(2);
                }
            Element seconds = div.child(2);
            Element title = seconds.child(0);
            Element three = seconds.child(1);
            Element content = three.child(0);
            Element four = seconds.child(3);
            Element xilike = four.child(0);
            Element comment = four.child(2);
            System.out.println(cover.attr("src"));
        });



    }
}
