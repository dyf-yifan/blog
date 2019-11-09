package com.scs.web.blog.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author 丁怡凡
 * @Desprition:Jsoup的解析器练习
 */
public class JsoupDemo {
    public static void main(String[] args) throws Exception{
        //声明
        Document document;
        document = Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users").get();
        //选取所有class为col-xs-8的元素组合
        Elements divs = document.getElementsByClass("col-xs-8");
        //对divs进行遍历
        divs.forEach(div->{
            //取出class为wrap的节点
            Element wrapDiv = div.child(0);
//            System.out.println(wrapDiv.html());
            Element link = wrapDiv.child(0);
            Elements linkchildren = link.children();
            System.out.println(linkchildren.get(0).attr("src"));

            System.out.println(linkchildren.get(0).text());
        });
    }
}
