package com.scs.web.blog.dao;

import cn.hutool.db.Entity;
import com.scs.web.blog.entity.Article;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 丁怡凡
 */
public interface ArticleDao {
    /**
     *
     * @param article
     * @return
     * @throws SQLException
     */
    int insert(Article article) throws SQLException;

    /**
     *
     * @param articleList
     * @return
     * @throws SQLException
     */
    int[] batchInsert(List<Article> articleList) throws SQLException;

    /**
     *
     * @param title
     * @return
     * @throws SQLException
     */
    Article findArticleByTitle(String title) throws SQLException;
    List<Entity> selectAll() throws SQLException;
//    Entity getArticle(int id) throws SQLException;

}
