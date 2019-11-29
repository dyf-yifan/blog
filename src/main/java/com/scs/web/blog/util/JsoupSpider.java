package com.scs.web.blog.util;

import com.scs.web.blog.entity.Article;
import com.scs.web.blog.entity.Theme;
import com.scs.web.blog.entity.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 丁怡凡
 */
public class JsoupSpider {
    private static Logger logger = LoggerFactory.getLogger(JsoupSpider.class);
    private static final int PAGE_COUNT = 1;

    public static List<User> getUsers() {
        Document document = null;
        List<User> userList = new ArrayList<>(100);
        for (int i = 1; i <= 3; i++) {
            try {
                document = Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users&page=" + i).get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            Elements divs = document.getElementsByClass("col-xs-8");
            divs.forEach(div -> {
                Element wrapDiv = div.child(0);
                Element link = wrapDiv.child(0);
                Elements linkChildren = link.children();
                Element linka = div.child(0).child(0).select("a").get(0);
                User user = new User();
                user.setAddress(DataUitl.getAddress());
                user.setMobile(DataUitl.getMobile());
                user.setPassword(DataUitl.getPassword());
                user.setGender(DataUitl.getGender());
                user.setAvatar("https:" + linkChildren.get(0).attr("src"));
                user.setNickname(linkChildren.get(1).text());
                user.setIntroduction(linkChildren.get(2).text());
                user.setBirthday(DataUitl.getBirthday());
                user.setCreateTime(LocalDateTime.now());
                user.setStatus(DataUitl.getStatus());
                user.setFollows(DataUitl.getDia());
                user.setFans(DataUitl.getDia());
                user.setArticles(DataUitl.getDia());
                user.setFNumber(DataUitl.getUserId());
                user.setAchieveLike(DataUitl.getUserId());
                user.setTotal(DataUitl.getUserId());
                user.setEmail(DataUitl.getEmail(0,10));
                user.setBanner("https:" + linkChildren.get(0).attr("src"));
                user.setHomepage("https://www.jianshu.com/u"+linka.attr("href").substring(6));
                userList.add(user);
            });
        }
        return userList;
    }

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
            Element link = div.child(1).child(1).select("a").get(0);
            Theme theme = new Theme();
            theme.setPic(pic.attr("src"));
            theme.setThName(thname.text());
            theme.setThTime(thtime.text());
            theme.setLook(look.text());
            theme.setProduction(production.text());
            theme.setThNumber(DataUitl.getDiamond());
            theme.setAttention(DataUitl.getDiamond());
            theme.setAdminId(1L);
            theme.setHomepage("https://www.zhihu.com"+ link.attr("href"));
            themeList.add(theme);
        });

        return themeList;
    }

    public static void main(String[] args) {
        System.out.println(JsoupSpider.getUsers().get(1));
    }


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
                Element time;
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
                Element unlikes = div.child(2).child(3).child(1);
                if(unlikes != null){
                    unlikes = div.child(2).child(3).child(1);
                }else {
                    unlikes = div.child(2).child(3);
                }
                Article article = new Article();
                article.setUserId(DataUitl.getUserId());
                article.setUnlikes(unlikes.text());
                article.setTitle(title.text());
                article.setContent(content.text());
                article.setCover(cover.attr("src"));
                article.setDiamond(DataUitl.getDiamond());
                article.setPublishTime(time.text());
                article.setThemeId(DataUitl.getThemeId());
                article.setUserId(DataUitl.getUserId());
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

