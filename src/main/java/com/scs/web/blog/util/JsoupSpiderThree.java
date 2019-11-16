package com.scs.web.blog.util;

import com.scs.web.blog.entity.Theme;
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
 * @ClassName JsoupSpiderThree
 * @Description 专题数据爬取
 * @Author ding
 * @Date 2019/11/15 11:00
 * @Version 1.0
 **/
public class JsoupSpiderThree {
    private static Logger logger = LoggerFactory.getLogger(JsoupSpiderThree.class);
    public static List<Theme> getThemes() {
        Document document = null;
        List<Theme> themeList = new ArrayList<>(100);
            try {
                document = Jsoup.connect("https://www.zhihu.com/special/all").get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            Elements divs = document.getElementsByClass("SpecialListCard SpecialListPage-specialCard");
        System.out.println(divs.size());
            divs.forEach(div -> {
                Element pic = div.child(0).child(0);
                Element thname = div.child(1).child(0).child(0).child(0);
                Element thtime = div.child(1).child(0).child(0).child(1).child(0);
                Element look = div.child(1).child(0).child(0).child(1).child(1);
                Element production = div.child(1).child(1);
                Theme theme = new Theme();
                theme.setId(DataUitl.getId());
                theme.setPic(pic.attr("src"));
                theme.setThname(thname.text());
                theme.setThtime(thtime.text());
                theme.setLook(look.text());
                theme.setProduction(production.text());
                theme.setThnumber(DataUitl.getDiamond());
                theme.setAttention(DataUitl.getDiamond());
                themeList.add(theme);
            });

        return themeList;
    }

    public static void main(String[] args) {
        Document document = null;
        List<Theme> themeList = new ArrayList<>(100);
        try {
            document = Jsoup.connect("https://www.zhihu.com/api/v4/news_specials/list?limit=10&offset=400").get();
        } catch (IOException e) {
            logger.error("连接失败");
        }

        Elements titles = document.getElementsByClass("type-string");
        titles.forEach(title -> {
            System.out.println(title.text());
        });
    }
}
