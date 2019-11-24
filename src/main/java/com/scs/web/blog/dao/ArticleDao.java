package com.scs.web.blog.dao;

import com.scs.web.blog.domain.vo.ArticleVo;
import com.scs.web.blog.entity.Article;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 丁怡凡
 */
public interface ArticleDao {
//    /**
//     * 插入文章
//     * @param article
//     * @return
//     * @throws SQLException
//     */
//    int insert(Article article) throws SQLException;

//    /**
//     * 查询所有文章
//     * @return
//     * @throws SQLException
//     */
//    List<Entity> selectAll() throws SQLException;

    /**
     * 批量新增文章
     * @param articleList
     * @return
     * @throws SQLException
     */
    void batchInsert(List<Article> articleList) throws SQLException;

    /**
     * 查询热门文章，返回视图集合
     * @return
     * @throws SQLException
     */
    List<ArticleVo> selectHotArticles() throws SQLException;

    /**
     * 分页获得文章数据
     * @param currentPage
     * @param count
     * @return
     * @throws SQLException
     */
    List<ArticleVo> selectByPage(int currentPage, int count) throws SQLException;

    /**
     * 根据关键字模糊查询所有文章
     * @param keywords
     * @return
     * @throws SQLException
     */
    List<ArticleVo> selectByKeywords(String keywords) throws SQLException;

    /**
     * 根据专题id查询所有文章
     * @param themeId
     * @return
     * @throws SQLException
     */
    List<ArticleVo> selectByThemeId(long themeId) throws SQLException;

    /**
     * 根据专题id查询所有文章
     * @param userId
     * @return
     * @throws SQLException
     */
    List<ArticleVo> selectByUserId(long userId) throws SQLException;

    /**
     * 根据id获取文章详情
     * @param id
     * @return
     * @throws SQLException
     */
    ArticleVo getArticle(long id) throws SQLException;
}
