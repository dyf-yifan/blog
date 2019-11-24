package com.scs.web.blog.dao;

import com.scs.web.blog.domain.vo.ThemeVo;
import com.scs.web.blog.entity.Theme;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 丁怡凡
 */
public interface ThemeDao {
    /**
     * 批量新增专题
     * @param themeList
     * @return
     * @throws SQLException
     */
    void batchInsert(List<Theme> themeList) throws SQLException;
//    List<Entity> selectAll() throws SQLException;

    int insert(Theme theme) throws SQLException;
    /**
     * 获取所有专题
     * @return
     * @throws SQLException
     */
    List<Theme> selectAll() throws SQLException;
    /**
     * 获取热门专题
     * @return
     * @throws SQLException
     */
    List<Theme> selectHotThemes() throws SQLException;

    /**
     * 分页显示专题
     * @param currentPage
     * @param count
     * @return
     * @throws SQLException
     */
    List<Theme> selectByPage(int currentPage,int count) throws SQLException;

    /**
     * 根据id获取专题详情
     * @param id
     * @return
     * @throws SQLException
     */
    ThemeVo getTheme(long id) throws SQLException;

    /**
     * 模糊搜索专题
     * @param keywords
     * @return
     * @throws SQLException
     */
    List<Theme> selectByKeywords(String keywords) throws SQLException;
}
