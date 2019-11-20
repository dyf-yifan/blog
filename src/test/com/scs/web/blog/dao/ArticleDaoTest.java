package com.scs.web.blog.dao;

import com.scs.web.blog.domain.vo.ArticleVo;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.util.JsoupSpiderTwo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class ArticleDaoTest {
    private static Logger logger = (Logger) LoggerFactory.getLogger(ArticleDaoTest.class);
    private ArticleDao articleDao = DaoFactory.getArticleDaoInstance();
    @Test
    public void batchInsert() {
            try{
                int[] result = articleDao.batchInsert((JsoupSpiderTwo.getArticles()));
            }catch (SQLException e){
                logger.error("批量新增图书出现异常");
                e.printStackTrace();
            }
    }
    @Test
    public void selectHotArticles() throws SQLException {
        List<ArticleVo> articleVoList = articleDao.selectHotArticles();
        articleVoList.forEach(a -> System.out.println(a));
    }
}