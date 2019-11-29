package com.scs.web.blog.dao.impl;

import com.scs.web.blog.dao.UserDao;
import com.scs.web.blog.domain.vo.UserVo;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.util.BeanHandler;
import com.scs.web.blog.util.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserDaoImpl
 * @Description UserDao数据访问对象接口实现类
 * @Author ding
 * @Date 2019/11/9 14:38
 * @Version 1.0
 **/
public class UserDaoImpl implements UserDao {
    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
//    @Override
//    public int insertUser(User user) throws SQLException {
//        Connection connection = DbUtil.getConnection();
//        String sql = "INSERT INTO t_user (mobile,password,nickname,gender,address,introduction) VALUES (?,?,?,?,?,?) ";
//        PreparedStatement pstmt = connection.prepareStatement(sql);
//        pstmt.setString(1, user.getMobile());
//        pstmt.setString(2, user.getPassword());
//        pstmt.setString(3,user.getNickname());
//        pstmt.setString(4,user.getGender());
//        pstmt.setString(5,user.getAddress());
//        pstmt.setString(6,user.getIntroduction());
//        int n = pstmt.executeUpdate();
//        DbUtil.close(null, pstmt, connection);
//        return n;
//    }

    @Override
    public void insert(User user) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = " INSERT INTO t_user (mobile,password) VALUES (?,?) ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, user.getMobile());
        pst.setString(2, user.getPassword());
        int n = pst.executeUpdate();
        DbUtil.close(connection,pst);
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> userList = new ArrayList<>();
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = "SELECT * FROM t_user" ;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setMobile(rs.getString("mobile"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setAvatar(rs.getString("avatar"));
                user.setGender(rs.getString("gender"));
                user.setBirthday(rs.getDate("birthday").toLocalDate());
                user.setAddress(rs.getString("address"));
                user.setIntroduction(rs.getString("introduction"));
                user.setHomepage(rs.getString("homepage"));
                user.setFollows(rs.getShort("follows"));
                user.setFans(rs.getShort("fans"));
                user.setArticles(rs.getShort("articles"));
                user.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
                user.setStatus(rs.getShort("status"));
//                user.setFNumber(rs.getLong("f_umber"));
                user.setAchieveLike(rs.getLong("achieve_like"));
                user.setTotal(rs.getLong("total"));
                user.setBanner(rs.getString("banner"));
                user.setEmail(rs.getString("email"));
                userList.add(user);
        }
        connection.commit();
        return userList;

    }

    @Override
    public void batchInsert(List<User> userList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = " INSERT INTO t_user (mobile,password,nickname,avatar,gender,birthday,address,introduction,homepage,follows,fans,articles,create_time,status,f_number,achieve_like,total,banner,email) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement pst = connection.prepareStatement(sql);
        userList.forEach(user -> {
            try {
                pst.setString(1, user.getMobile());
                pst.setString(2, user.getPassword());
                pst.setString(3, user.getNickname());
                pst.setString(4, user.getAvatar());
                pst.setString(5, user.getGender());
                pst.setObject(6, user.getBirthday());
                pst.setString(7,user.getAddress());
                pst.setString(8, user.getIntroduction());
                pst.setString(9,user.getHomepage());
                pst.setInt(10,user.getFollows());
                pst.setInt(11,user.getFans());
                pst.setInt(12,user.getArticles());
                pst.setObject(13, user.getCreateTime());
                pst.setShort(14,user.getStatus());
                pst.setLong(15,user.getFNumber());
                pst.setLong(16,user.getAchieveLike());
                pst.setLong(17,user.getTotal());
                pst.setString(18,user.getBanner());
                pst.setString(19,user.getEmail());
                pst.addBatch();
            } catch (SQLException e) {
                logger.error("批量加入用户数据出现异常");
            }
        });
        pst.executeBatch();
        connection.commit();
        DbUtil.close(connection,pst);
    }

    @Override
    public User findUserByMobile(String mobile) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = " SELECT * FROM t_user WHERE mobile = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, mobile);
        ResultSet rs = pst.executeQuery();
        User user = BeanHandler.convertUser(rs).get(0);
        DbUtil.close(connection,pst,rs);
        return user;
    }

    /**
     * 获取热门用户
     * @return
     * @throws SQLException
     */
    @Override
    public List<User> selectHotUsers() throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = " SELECT * FROM t_user ORDER BY fans DESC LIMIT 10 ";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        List<User> userList = BeanHandler.convertUser(rs);
        DbUtil.close(connection,pst,rs);
        return userList;
    }

    @Override
    public List<User> selectByPage(int currentPage, int count) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = " SELECT * FROM t_user LIMIT ?,? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1,(currentPage - 1)* count);
        pst.setInt(2,count);
        ResultSet rs = pst.executeQuery();
        List<User> userList = BeanHandler.convertUser(rs);
        DbUtil.close(connection,pst,rs);
        return userList;
    }

    @Override
    public UserVo getUser(long id) {
        Connection connection = DbUtil.getConnection();
        String sql = " SELECT * FROM t_user WHERE id = ? ";
        PreparedStatement pst = null;
        UserVo userVo = new UserVo();
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            User user = BeanHandler.convertUser(rs).get(0);
            userVo.setUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbUtil.close(connection, pst);
        return userVo;
    }

    @Override
    public List<User> selectByKeywords(String keywords) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = " SELECT * FROM t_user " + " WHERE nickname LIKE ? OR introduction LIKE ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1,"%" + keywords + "%");
        pst.setString(2,"%" + keywords + "%");
        ResultSet rs = pst.executeQuery();
        List<User> userList = BeanHandler.convertUser(rs);
        DbUtil.close(connection,pst,rs);
        return userList;
    }

}
