package com.scs.web.blog.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.scs.web.blog.dao.UserDao;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.util.DbUtil;

import java.sql.*;
import java.util.List;

/**
 * @ClassName UserDaoImpl
 * @Description TODO
 * @Author
 * @Date 2019/11/9 14:38
 * @Version 1.0
 **/
public class UserDaoImpl implements UserDao {
    @Override
    public int insertUser(User user) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO t_user (mobile,password,nickname,gender,address,introduction) VALUES (?,?,?,?,?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, user.getMobile());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3,user.getNickname());
        pstmt.setString(4,user.getGender());
        pstmt.setString(5,user.getAddress());
        pstmt.setString(6,user.getIntroduction());
        int n = pstmt.executeUpdate();
        DbUtil.close(null, pstmt, connection);
        return n;
    }

    @Override
    public int insert(User user) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO t_user (mobile,password) VALUES (?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, user.getMobile());
        pstmt.setString(2, user.getPassword());
        int n = pstmt.executeUpdate();
        DbUtil.close(null, pstmt, connection);
        return n;
    }

    @Override
    public int[] batchInsert(List<User> userList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO t_user (mobile,password,nickname,avatar,gender,birthday,introduction,create_time) VALUES (?,?,?,?,?,?,?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);

        userList.forEach(user -> {
            try {
                pstmt.setString(1, user.getMobile());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getNickname());
                pstmt.setString(4, user.getAvatar());
                pstmt.setString(5, user.getGender());
                //日期的设置，可以使用setObject
                pstmt.setObject(6, user.getBirthday());
                pstmt.setString(7, user.getIntroduction());
                pstmt.setObject(8, user.getCreateTime());
                pstmt.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        int[] result = pstmt.executeBatch();
        connection.commit();
        DbUtil.close(null,pstmt,connection);
        return result;
    }

    @Override
    public User findUserByMobile(String mobile) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user WHERE mobile = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, mobile);
        ResultSet rs = pstmt.executeQuery();
        User user = null;
        if (rs.next()) {
            user = new User();
            user.setId(rs.getLong("id"));
            user.setMobile(rs.getString("mobile"));
            user.setPassword(rs.getString("password"));
            user.setNickname(rs.getString("nickname"));
            user.setAvatar(rs.getString("avatar"));
            user.setGender(rs.getString("gender"));
            user.setBirthday(rs.getDate("birthday").toLocalDate());
            user.setIntroduction(rs.getString("introduction"));
            user.setAddress(rs.getString("address"));
            user.setFollows(rs.getShort("follows"));
            user.setFans(rs.getShort("fans"));
            user.setArticles(rs.getShort("articles"));
            user.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            user.setStatus(rs.getShort("status"));
        }
        return user;
    }

    @Override
    public List<Entity> selectAll() throws SQLException {
        return Db.use().query("SELECT * from t_user ORDER BY id DESC");
    }
}
