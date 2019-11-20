package com.scs.web.blog.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.domain.vo.ArticleVo;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ArticleDaoImpl
 * @Description TODO
 * @Author ding
 * @Date 2019/11/10 11:37
 * @Version 1.0
 **/
public class ArticleDaoImpl implements ArticleDao {
    @Override
    public int insert(Article article) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO article (title,content) VALUES (?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, article.getTitle());
        pstmt.setString(2, article.getContent());
        int n = pstmt.executeUpdate();
        DbUtil.close(null, pstmt, connection);
        return n;
    }

    @Override
    public Article findArticleByTitle(String title) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM article WHERE title = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, title);
        ResultSet rs = pstmt.executeQuery();
        Article article = null;
        if (rs.next()) {
            article = new Article();
            article.setId(rs.getLong("id"));
            article.setTitle(rs.getString("title"));
            article.setContent(rs.getString("content"));
            article.setCover(rs.getString("cover"));
            article.setDiamond(rs.getInt("diamond"));
            article.setNickname(rs.getString("nickname"));
//           (rs.getDate("birthday").toLocalDate());
            article.setComments(rs.getInt("comments"));
            article.setLikes(rs.getInt("likes"));
            article.setPublish_time(rs.getString("publish_time"));
            article.setUseravatar(rs.getString("useravatar"));
            article.setUserid(rs.getInt("userid"));
            article.setUnlikes(rs.getString("unlikes"));
        }
        return article;
    }

    @Override
    public List<Entity> selectAll() throws SQLException {
            return Db.use().query("SELECT * from article ORDER BY id DESC");
    }

    @Override
    public List<ArticleVo> selectHotArticles() throws SQLException {
        List<ArticleVo> articleVoList = new ArrayList<>(20);
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT a.id,a.userid,a.title,a.cover,a.nickname,a.content,a.likes,a.unlikes,a.useravatar,a.diamond,b.id,b.nickname,b.avatar\n"+
                "FROM article a\n" +
                "LEFT JOIN t_user b\n" +
                "ON a.userid = b.id\n" +
                "ORDER BY a.diamond DESC LIMIT 20";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            ArticleVo article = new ArticleVo();
            article.setId(rs.getLong("id"));
            article.setUserid(rs.getInt("userid"));
            article.setTitle(rs.getString("title"));
            article.setCover(rs.getString("cover"));
            article.setContent(rs.getString("content"));
            article.setLikes(rs.getString("likes"));
            article.setUnlikes(rs.getString("unlikes"));
            article.setUseravatar(rs.getString("useravatar"));
            article.setDiamond(rs.getInt("diamond"));
            articleVoList.add(article);
        }
        return articleVoList;
    }

    @Override
    public int[] batchInsert(List<Article> articleList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO article (title,content,cover,diamond,nickname,comments,likes,publish_time,userid,useravatar,unlikes) VALUES (?,?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);

        articleList.forEach(article -> {
            try {
                pstmt.setString(1, article.getTitle());
                pstmt.setString(2, article.getContent());
                pstmt.setString(3, article.getCover());
                pstmt.setInt(4, article.getDiamond());
                pstmt.setString(5, article.getNickname());
                pstmt.setInt(6, article.getComments());
                pstmt.setInt(7, article.getLikes());
                pstmt.setString(8, article.getPublish_time());
                pstmt.setInt(9,article.getUserid());
                pstmt.setString(10,article.getUseravatar());
                pstmt.setString(11,article.getUnlikes());
                pstmt.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        int[] result = pstmt.executeBatch();
        connection.commit();
        DbUtil.close(null,pstmt,connection);
        return result;
    }


}
