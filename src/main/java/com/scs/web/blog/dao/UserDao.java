package com.scs.web.blog.dao;

import cn.hutool.db.Entity;
import com.scs.web.blog.domain.vo.UserVo;
import com.scs.web.blog.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * UserDao数据访问对象接口
 * @author 丁怡凡
 */
public interface UserDao {
//    /**
//     * 查找所有用户
//     * @return List
//     * @throws SQLException
//     */
//    List<User> findAll() throws SQLException;
 /**
 * 批量新增用户
 * @param userList
 * @return
 * @throws SQLException
 */
void batchInsert(List<User> userList) throws SQLException;
    /**
     * 新增用户
     * @param user
     * @return
     * @throws SQLException
     */
    void insert(User user) throws SQLException;

    /**
     * 根据手机号查找用户
     * @param mobile
     * @return
     * @throws SQLException
     */
    User findUserByMobile(String mobile) throws SQLException;

    /**
     * 查询所有用户
     * @return
     * @throws SQLException
     */
    List<Entity> selectAll() throws SQLException;

    /**
     * 查询热门用户
     * @return
     * @throws SQLException
     */
    List<User> selectHotUsers() throws SQLException;

    /**
     * 查询分页用户
     * @param currentPage
     * @param count
     * @return
     * @throws SQLException
     */
     List<User> selectByPage(int currentPage, int count) throws SQLException;

    /**
     * 根据id查询用户详情，包括其他数据
     * @param id
     * @return
     * @throws SQLException
     */
     UserVo getUser(long id) ;

    /**
     * 模糊搜索用户
     * @param keywords
     * @return
     * @throws SQLException
     */
     List<User> selectByKeywords(String keywords) throws SQLException;
}
