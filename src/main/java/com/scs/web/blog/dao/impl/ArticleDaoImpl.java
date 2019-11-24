package com.scs.web.blog.dao.impl;

import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.domain.vo.ArticleVo;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.util.BeanHandler;
import com.scs.web.blog.util.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ArticleDaoImpl
 * @Description TODO
 * @Author ding
 * @Date 2019/11/10 11:37
 * @Version 1.0
 **/
public class ArticleDaoImpl implements ArticleDao {
    private static Logger logger = LoggerFactory.getLogger(ArticleDaoImpl.class);
//    @Override
//    public int insert(Article article) throws SQLException {
//        Connection connection = DbUtil.getConnection();
//        String sql = "INSERT INTO article (title,content) VALUES (?,?) ";
//        PreparedStatement pst = connection.prepareStatement(sql);
//        pst.setString(1, article.getTitle());
//        pst.setString(2, article.getContent());
//        int n = pst.executeUpdate();
//        DbUtil.close(connection,pst);
//        return n;
//    }

//    @Override
//    public List<Entity> selectAll() throws SQLException {
//            return Db.use().query("SELECT * from article ORDER BY id DESC");
//    }

    @Override
    public List<ArticleVo> selectHotArticles() throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT a.id,a.user_id,a.theme_id,a.title,a.content,a.likes,a.unlikes,a.diamond,a.publish_time,"+"b.th_name,b.pic,c.nickname,c.avatar "+
                "FROM article a " +
                "LEFT JOIN theme b " +
                "ON a.theme_id = b.id " +
                "LEFT JOIN t_user c "+
                "ON a.user_id = c.id "+
                "ORDER BY a.diamond DESC "+
                "LIMIT 10 ";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        List<ArticleVo> articleVoList = BeanHandler.convertArticle(rs);
        DbUtil.close(connection,pst,rs);
        return articleVoList;
    }

    @Override
    public List<ArticleVo> selectByPage(int currentPage, int count) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT a.*,b.th_name,b.pic,c.nickname,c.avatar " +
                "FROM article a " +
                "LEFT JOIN theme b " +
                "ON a.theme_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "ORDER BY a.id  LIMIT ?,? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, (currentPage - 1) * count);
        pst.setInt(2, count);
        ResultSet rs = pst.executeQuery();
        List<ArticleVo> articleVos = BeanHandler.convertArticle(rs);
        DbUtil.close(connection, pst, rs);
        return articleVos;
    }

    @Override
    public List<ArticleVo> selectByKeywords(String keywords) throws SQLException {
        Connection connection = DbUtil.getConnection();
        //从文章、专题、用户表联查出前端需要展示的数据
        String sql = "SELECT a.*,b.th_name,b.pic,c.nickname,c.avatar " +
                "FROM article a " +
                "LEFT JOIN theme b " +
                "ON a.theme_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "WHERE a.title LIKE ?  OR a.diamond LIKE ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, "%" + keywords + "%");
        pst.setString(2, "%" + keywords + "%");
        ResultSet rs = pst.executeQuery();
        List<ArticleVo> articleVos = BeanHandler.convertArticle(rs);
        DbUtil.close(connection, pst, rs);
        return articleVos;
    }

    @Override
    public List<ArticleVo> selectByThemeId(long themeId) throws SQLException {
        Connection connection = DbUtil.getConnection();
        //从文章、专题、用户表联查出前端需要展示的数据
        String sql = "SELECT a.*,b.th_name,b.pic,c.nickname,c.avatar " +
                "FROM article a " +
                "LEFT JOIN theme b " +
                "ON a.theme_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "WHERE a.theme_id = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, themeId);
        ResultSet rs = pst.executeQuery();
        List<ArticleVo> articleVos = BeanHandler.convertArticle(rs);
        DbUtil.close(connection, pst, rs);
        return articleVos;
    }

    @Override
    public List<ArticleVo> selectByUserId(long userId) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT a.*,b.th_name,b.pic,c.nickname,c.avatar "+
                "FROM article a "+
                "LEFT JOIN theme b "+
                "ON a.theme_id = b.id "+
                "LEFT JOIN t_user c "+
                "ON a.user_id = c.id "+
                "WHERE a.theme_id = ? ";
        PreparedStatement pst  =connection.prepareStatement(sql);
        pst.setLong(1,userId);
        ResultSet rs = pst.executeQuery();
        List<ArticleVo> articleVos = BeanHandler.convertArticle(rs);
        DbUtil.close(connection,pst,rs);
        return articleVos;
    }

    @Override
    public ArticleVo getArticle(long id) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT a.*,b.th_name,b.pic,c.nickname,c.avatar " +
                "FROM article a " +
                "LEFT JOIN theme b " +
                "ON a.theme_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "WHERE a.id = ?  ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        ArticleVo articleVo = BeanHandler.convertArticle(rs).get(0);
        rs.previous();
        articleVo.getArticle().setContent(rs.getString("content"));
        DbUtil.close(connection, pst, rs);
        return articleVo;
    }
    /**
     *
     * @param articleList
     * @throws SQLException
     */
    @Override
    public void batchInsert(List<Article> articleList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO article (title,content,cover,diamond,comments,likes,publish_time,user_id,unlikes) VALUES (?,?,?,?,?,?,?,?,?) ";
        PreparedStatement pst = connection.prepareStatement(sql);
        articleList.forEach(article -> {
            try {
                pst.setString(1, article.getTitle());
                pst.setString(2, article.getContent());
                pst.setString(3, article.getCover());
                pst.setInt(4, article.getDiamond());
                pst.setInt(5, article.getComments());
                pst.setInt(6, article.getLikes());
                pst.setString(7, article.getPublishTime());
                pst.setLong(8,article.getUserId());
                pst.setString(9,article.getUnlikes());
                pst.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        pst.executeBatch();
        connection.commit();
        DbUtil.close(connection,pst);
    }
}
