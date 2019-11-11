package com.scs.web.blog.factory;

import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.dao.UserDao;
import com.scs.web.blog.dao.impl.ArticleDaoImpl;
import com.scs.web.blog.dao.impl.UserDaoImpl;

/**
 * @author 丁怡凡
 */
public class DaoFactory {
        public static UserDao getUserDaoInstance() {
        return new UserDaoImpl();
    }
    public static ArticleDao getArticleDaoInstance() {
        return new ArticleDaoImpl();
    }

}

