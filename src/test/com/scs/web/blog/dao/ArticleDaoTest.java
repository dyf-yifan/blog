package com.scs.web.blog.dao;

import com.scs.web.blog.domain.vo.ArticleVo;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.util.JsoupSpider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class ArticleDaoTest {
    private static Logger logger = (Logger) LoggerFactory.getLogger(ArticleDaoTest.class);
    private ArticleDao articleDao = DaoFactory.getArticleDaoInstance();
    @Test
    public void batchInsert() throws SQLException {
        articleDao.batchInsert(JsoupSpider.getArticles());
    }
    @Test
    public void selectHotArticles() throws SQLException {
        List<ArticleVo> articleVoList = articleDao.selectHotArticles();
        System.out.println(articleVoList.size());
    }
@Test
public void getArticle() throws SQLException {
    ArticleVo article = articleDao.getArticle(1);
    System.out.println(article);
}
@Test
public void selectByKeywords() throws SQLException {
    List<ArticleVo> articleVoList = articleDao.selectByKeywords("有");
    System.out.println(articleVoList.size());
}

}