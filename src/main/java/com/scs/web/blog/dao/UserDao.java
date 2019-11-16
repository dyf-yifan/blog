package com.scs.web.blog.dao;

import cn.hutool.db.Entity;
import com.scs.web.blog.entity.User;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 丁怡凡
 */
public interface UserDao {
 /**
 * 批量新增用户
 * @param userList
 * @return
 * @throws SQLException
 */
int[] batchInsert(List<User> userList) throws SQLException;
    /**
     * 新增用户
     * @param user
     * @return
     * @throws SQLException
     */
    int insert(User user) throws SQLException;
    int insertUser(User user) throws SQLException;



    /**
     * 根据手机号查找用户
     * @param mobile
     * @return
     * @throws SQLException
     */
    User findUserByMobile(String mobile) throws SQLException;
    List<Entity> selectAll() throws SQLException;

}
