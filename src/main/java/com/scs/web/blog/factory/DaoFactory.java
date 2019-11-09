package com.scs.web.blog.factory;

import com.scs.web.blog.dao.UserDao;
import com.scs.web.blog.dao.impl.UserDaoImpl;

/**
 * @author 丁怡凡
 */
public class DaoFactory {
//    public static StudentDao getStudentDaoInstance() {
//
//        return new StudentDaoImpl();
//    }
    public static UserDao getUserDaoInstance() {
        return new UserDaoImpl();
    }

}

