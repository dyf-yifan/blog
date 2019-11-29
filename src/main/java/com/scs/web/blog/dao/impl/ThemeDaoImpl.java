package com.scs.web.blog.dao.impl;

import com.scs.web.blog.dao.ThemeDao;
import com.scs.web.blog.domain.vo.ThemeVo;
import com.scs.web.blog.entity.Theme;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.util.BeanHandler;
import com.scs.web.blog.util.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ThemeDaoImpl
 * @Description TODO
 * @Author ding
 * @Date 2019/11/15 14:53
 * @Version 1.0
 **/
public class ThemeDaoImpl implements ThemeDao {
    private static Logger logger = LoggerFactory.getLogger(ThemeDaoImpl.class);

    @Override
    public void batchInsert(List<Theme> themeList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO theme (th_name,pic,attention,th_number,th_time,look,production,admin_id,homepage) VALUES (?,?,?,?,?,?,?,?,?) ";
        PreparedStatement pst = connection.prepareStatement(sql);
        themeList.forEach(theme -> {
            try {
                pst.setString(1,theme.getThName());
                pst.setString(2,theme.getPic());
                pst.setInt(3,theme.getAttention());
                pst.setInt(4,theme.getThNumber());
                pst.setString(5,theme.getThTime());
                pst.setString(6,theme.getLook());
                pst.setString(7,theme.getProduction());
                pst.setLong(8,theme.getAdminId());
                pst.setString(9,theme.getHomepage());
                pst.addBatch();
            } catch (SQLException e) {
                logger.error("批量加入专题信息出现异常");
            }
        });
        pst.executeBatch();
        connection.commit();
        DbUtil.close(connection,pst);
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Theme> selectAll() throws SQLException {
    Connection connection = DbUtil.getConnection();
    String sql = "SELECT * FROM theme ORDER BY id ";
    PreparedStatement pst = connection.prepareStatement(sql);
    ResultSet rs = pst.executeQuery();
    List<Theme> themeList = BeanHandler.convertTheme(rs);
    DbUtil.close(connection, pst, rs);
    return themeList;
}
    @Override
    public int insert(Theme theme) throws SQLException {
            Connection connection = DbUtil.getConnection();
            String sql = "INSERT INTO theme (th_name,th_number) VALUES (?,?) ";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, theme.getThName());
            pst.setInt(2, theme.getThNumber());
            int n = pst.executeUpdate();
            DbUtil.close(connection,pst);
            return n;
        }

    @Override
    public List<Theme> selectHotThemes() throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM theme ORDER BY attention DESC LIMIT 8 ";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        List<Theme> themeList = BeanHandler.convertTheme(rs);
        DbUtil.close(connection,pst,rs);
        return themeList;
    }

    @Override
    public List<Theme> selectByPage(int currentPage, int count) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM theme ORDER BY id LIMIT ?,? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1,(currentPage - 1)* count);
        pst.setInt(2,count);
        ResultSet rs = pst.executeQuery();
        List<Theme> themeList = BeanHandler.convertTheme(rs);
        DbUtil.close(connection,pst,rs);
        return themeList;
    }

    @Override
    public List<Theme> findAll() throws SQLException {
        List<Theme> themeList = new ArrayList<>();
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = "SELECT * FROM theme ";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            Theme theme = new Theme();
            theme.setId(rs.getLong("id"));
            theme.setThName(rs.getString("th_name"));
            theme.setPic(rs.getString("pic"));
            theme.setAttention(rs.getInt("attention"));
            theme.setThNumber(rs.getInt("th_number"));
            theme.setThTime(rs.getString("th_time"));
            theme.setLook(rs.getString("look"));
            theme.setProduction(rs.getString("production"));
            theme.setAdminId(rs.getLong("admin_id"));
            themeList.add(theme);
        }
        connection.commit();
        return themeList;
    }

    @Override
    public ThemeVo getTheme(long id) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT a.*,b.id,b.nickname,b.avatar "+" FROM theme a "+" LEFT JOIN t_user b "+" ON a.admin_id = b.id  "+" WHERE a.id = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1,id);
        ResultSet rs = pst.executeQuery();
        ThemeVo themeVo = null;
        if (rs.next()){
            themeVo = new ThemeVo();
            //专题基本信息
            Theme theme = new Theme();
            theme.setId(rs.getLong("id"));
            theme.setThName(rs.getString("th_name"));
            theme.setPic(rs.getString("pic"));
            theme.setAttention(rs.getInt("attention"));
            theme.setThNumber(rs.getInt("th_number"));
            theme.setThTime(rs.getString("th_time"));
            theme.setLook(rs.getString("look"));
            theme.setProduction(rs.getString("production"));
            theme.setAdminId(rs.getLong("admin_id"));
            theme.setHomepage(rs.getString("homepage"));
            themeVo.setTheme(theme);

            //管理员基本信息
            User admin = new User();
            admin.setId(rs.getLong("admin_id"));
            admin.setNickname(rs.getString("nickname"));
            admin.setAvatar(rs.getString("avatar"));
            themeVo.setAdmin(admin);
        }
        DbUtil.close(connection,pst,rs);
        return themeVo;
    }

    @Override
    public List<Theme> selectByKeywords(String keywords) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM theme " + " WHERE th_name LIKE ? OR production LIKE ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1,"%" + keywords + "%");
        pst.setString(2,"%" + keywords + "%");
        ResultSet rs = pst.executeQuery();
        List<Theme> themeList = BeanHandler.convertTheme(rs);
        DbUtil.close(connection,pst,rs);
        return themeList;
    }

}