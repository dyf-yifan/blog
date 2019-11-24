package com.scs.web.blog.util;

import com.scs.web.blog.domain.vo.ArticleVo;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.entity.Theme;
import com.scs.web.blog.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BeanHandler
 * @Description TODO
 * @Author ding
 * @Date 2019/11/24 10:02
 * @Version 1.0
 **/
public class BeanHandler {
    private static Logger logger = LoggerFactory.getLogger(BeanHandler.class);

    public static List<User> convertUser(ResultSet rs) {
        List<User> userList = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setMobile(rs.getString("mobile"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setAvatar(rs.getString("avatar"));
                user.setGender(rs.getString("gender"));
                user.setBirthday(rs.getDate("birthday").toLocalDate());
                user.setAddress(rs.getString("address"));
                user.setIntroduction(rs.getString("introduction"));
                user.setHomepage(rs.getString("homepage"));
                user.setFollows(rs.getShort("follows"));
                user.setFans(rs.getShort("fans"));
                user.setArticles(rs.getShort("articles"));
                user.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
                user.setStatus(rs.getShort("status"));
                user.setFNumber(rs.getLong("f_umber"));
                user.setAchieveLike(rs.getLong("achieve_like"));
                user.setTotal(rs.getLong("total"));
                user.setBanner(rs.getString("banner"));
                user.setEmail(rs.getString("email"));
                userList.add(user);
            }
        } catch (SQLException e) {
            logger.error("用户数据结果集解析产生异常");
        }
        return userList;
    }

    public static List<Theme> convertTheme(ResultSet rs) {
        List<Theme> themeList = new ArrayList<>();
        try {
            while (rs.next()) {
                Theme theme = new Theme();
                theme.setId(rs.getLong("id"));
                theme.setThName(rs.getString("th_name"));
                theme.setPic(rs.getString("pic"));
                theme.setAttention(rs.getInt("attention"));
                theme.setThNumber(rs.getInt("th_number"));
                theme.setThTime(rs.getString("th_time"));
                theme.setLook(rs.getString("look"));
                theme.setProduction(rs.getString("production"));
                theme.setAdminId(rs.getLong("admin_id"));
                themeList.add(theme);
            }
        } catch (SQLException e) {
            logger.error("专题数据结果集解析产生异常");
        }
        return themeList;
    }

    public static List<ArticleVo> convertArticle(ResultSet rs) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        try {
            while (rs.next()) {
                ArticleVo articleVo = new ArticleVo();
                //文章自身信息
                Article article = new Article();
                article.setId(rs.getLong("id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setCover(rs.getString("cover"));
                article.setDiamond(rs.getInt("diamond"));
                article.setComments(rs.getInt("comments"));
                article.setLikes(rs.getInt("likes"));
                article.setPublishTime(rs.getString("publish_time"));
                article.setUnlikes(rs.getString("unlikes"));

                //作者信息
                User author = new User();
                author.setId(rs.getLong("user_id"));
                author.setNickname(rs.getString("nickname"));
                author.setAvatar(rs.getString("avatar"));

                //专题信息
                Theme theme = new Theme();
                theme.setId(rs.getLong("theme_id"));
                theme.setThName(rs.getString("topic_name"));
                theme.setPic(rs.getString("pic"));

                //给文章视图对象设置三块内容
                articleVo.setArticle(article);
                articleVo.setAuthor(author);
                articleVo.setTheme(theme);
                //加入列表
                articleVoList.add(articleVo);
            }
        } catch (SQLException e) {
            logger.error("文章数据结果集解析异常");
        }
        return articleVoList;
    }
}
