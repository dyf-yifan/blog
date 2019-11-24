package com.scs.web.blog.dao;

import com.scs.web.blog.domain.vo.ThemeVo;
import com.scs.web.blog.entity.Theme;
import com.scs.web.blog.factory.DaoFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class ThemeDaoTest {
    private static Logger logger = (Logger) LoggerFactory.getLogger(ThemeDaoTest.class);
    private ThemeDao themeDao = DaoFactory.getThemeDaoInstance();
    @Test
    public void selectHotTopics() throws SQLException {
        List<Theme> themeList = themeDao.selectHotThemes();
        System.out.println(themeList.size());
    }
    @Test
    public void selectByKeywords() throws SQLException {
        List<Theme> themeList = themeDao.selectByKeywords("小");
        System.out.println(themeList.size());
    }
    @Test
    public void getTheme() throws SQLException {
        ThemeVo themeVo = themeDao.getTheme(1);
        System.out.println(themeVo);
    }
}