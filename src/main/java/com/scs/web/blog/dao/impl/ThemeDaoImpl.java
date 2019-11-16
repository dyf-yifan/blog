package com.scs.web.blog.dao.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.scs.web.blog.dao.ThemeDao;
import com.scs.web.blog.entity.Theme;
import com.scs.web.blog.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ThemeDaoImpl
 * @Description TODO
 * @Author ding
 * @Date 2019/11/15 14:53
 * @Version 1.0
 **/
public class ThemeDaoImpl implements ThemeDao {
    @Override
    public int[] batchInsert(List<Theme> themeList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO theme (thname,pic,attention,thnumber,thtime,production,look) VALUES (?,?,?,?,?,?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);

        themeList.forEach(theme -> {
            try {
                pstmt.setString(1,theme.getThname());
                pstmt.setString(2,theme.getPic());
                pstmt.setInt(3,theme.getAttention());
                pstmt.setInt(4,theme.getThnumber());
                pstmt.setString(5,theme.getThtime());
                pstmt.setString(6,theme.getLook());
                pstmt.setString(7,theme.getProduction());
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
    public Theme findThemeByThname(String thname) throws SQLException {
            Connection connection = DbUtil.getConnection();
            String sql = "SELECT * FROM theme WHERE thname = ? ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, thname);
            ResultSet rs = pstmt.executeQuery();
            Theme theme = null;
            if (rs.next()) {
                theme = new Theme();
                theme.setId(rs.getLong("id"));
                theme.setThname(rs.getString("thname"));
                theme.setPic(rs.getString("pic"));
                theme.setAttention(rs.getInt("attention"));
                theme.setThnumber(rs.getInt("thnumber"));
                theme.setThtime(rs.getString("thtime"));
                theme.setLook(rs.getString("look"));
                theme.setProduction(rs.getString("proroduction"));
            }
            return theme;
        }

    @Override
    public List<Entity> selectAll() throws SQLException {
        return Db.use().query("SELECT * from theme ORDER BY id DESC");
    }

    @Override
    public int insert(Theme theme) throws SQLException {
            Connection connection = DbUtil.getConnection();
            String sql = "INSERT INTO theme (thname,thnumber) VALUES (?,?) ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, theme.getThname());
            pstmt.setInt(2, theme.getThnumber());
            int n = pstmt.executeUpdate();
            DbUtil.close(null, pstmt, connection);
            return n;
        }
    }