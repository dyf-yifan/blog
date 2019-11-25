package com.scs.web.blog.dao;

import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.util.JsoupSpider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class UserDaoTest {
        private static Logger logger = (Logger) LoggerFactory.getLogger(UserDaoTest.class);
        private UserDao userDao = DaoFactory.getUserDaoInstance();
        @Test
        public void batchInsert() throws SQLException {
                userDao.batchInsert(JsoupSpider.getUsers());
        }
        @Test
        public void findUserByMobile() throws SQLException {
                User user = userDao.findUserByMobile("13900347723");
                System.out.println(user);
        }
        @Test
        public void selectHotUsers() throws SQLException {
        List<User> userList = userDao.selectHotUsers();
        userList.forEach(System.out::println);
                System.out.println(userList.size());
}
        @Test
        public void selectByKeywords() throws SQLException{
                List<User> userList = userDao.selectByKeywords("土");
                System.out.println(userList.size());
        }

}