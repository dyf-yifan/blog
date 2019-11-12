package com.scs.web.blog.util;

import com.scs.web.blog.entity.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName JsoupSpiderTwo
 * @Description TODO
 * @Author ding
 * @Date 2019/11/10 10:22
 * @Version 1.0
 **/
public class JsoupSpiderTwo {
    private static Logger logger = LoggerFactory.getLogger(JSoupSpider.class);

    public static List<Article> getArticles() {
        Document document = null;
        List<Article> articleList = new ArrayList<>(100);
        int j = 0;
        for (int i = 1; i <= 180; i++) {
            try {
                document = Jsoup.connect("https://book.douban.com/review/best/?start=" + i).get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            Elements divs = document.getElementsByClass("main review-item");
            divs.forEach(div -> {
                Element cover = div.child(0).child(0);
                Element one = div.child(1);
                Element name = one.child(1);
                Element time;
                System.out.println(one.childNodeSize());
                if (one.children().size() == 4) {
                    time = one.child(3);
                } else {
                    time = one.child(2);
                }
                Element seconds = div.child(2);
                Element title = seconds.child(0);
                Element three = seconds.child(1);
                Element content = three.child(0);
                Element four = seconds.child(3);
                Element xilike = four.child(0);
                Element comment = four.child(2);
                Article article = new Article();
                article.setTitle(title.text());
                article.setNickname(name.text());
                article.setContent(content.text());
                article.setCover(cover.attr("src"));
                article.setDiamond(DataUitl.getDiamond());
                article.setPublish_time(time.text());
                String a = comment.text();
                String comm = a.substring(0, a.length() - 2);
                article.setComments(Integer.valueOf(comm));
                article.setLikes(Integer.valueOf(xilike.text()));
                articleList.add(article);
            });
            j++;
            i = 2 * j * 10;

        }
        return articleList;
    }
}
