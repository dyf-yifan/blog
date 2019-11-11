package com.scs.web.blog.dao.impl;

import cn.hutool.db.Entity;
import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.factory.DaoFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ArticleDaoImplTest {
    private ArticleDao articleDao = DaoFactory.getArticleDaoInstance();
    @Test
    public void selectAll() throws SQLException {
        List<Entity> articleList = articleDao.selectAll();
        articleList.forEach(entity ->
                System.out.println(entity.get("title")+","+entity.get("article"))
        );
    }
}