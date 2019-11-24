package com.scs.web.blog.factory;

import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.dao.RegionDao;
import com.scs.web.blog.dao.ThemeDao;
import com.scs.web.blog.dao.UserDao;
import com.scs.web.blog.dao.impl.ArticleDaoImpl;
import com.scs.web.blog.dao.impl.RegionDaoImpl;
import com.scs.web.blog.dao.impl.ThemeDaoImpl;
import com.scs.web.blog.dao.impl.UserDaoImpl;

/**
 * Dao工厂类
 * @author 丁怡凡
 */
public class DaoFactory {
    public static UserDao getUserDaoInstance() {
        return new UserDaoImpl();
    }
    public static ArticleDao getArticleDaoInstance() {
        return new ArticleDaoImpl();
    }
    public static ThemeDao getThemeDaoInstance() {
        return new ThemeDaoImpl();
    }
    public static RegionDao getRegionDaoInstance() {
        return new RegionDaoImpl();
    }
}

