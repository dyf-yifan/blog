package com.scs.web.blog.dao.impl;

import cn.hutool.db.Entity;
import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.dao.ThemeDao;
import com.scs.web.blog.entity.Theme;
import com.scs.web.blog.factory.DaoFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ThemeDaoImplTest {
    private ThemeDao themeDao = DaoFactory.getThemeDaoInstance();
    @Test
    public void selectAll() throws SQLException {
        List<Entity> themeList = themeDao.selectAll();
        themeList.forEach(entity ->
                System.out.println(entity.get("thname")+","+entity.get("theme"))
        );
    }
}