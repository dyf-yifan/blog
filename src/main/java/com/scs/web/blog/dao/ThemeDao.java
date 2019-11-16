package com.scs.web.blog.dao;

import cn.hutool.db.Entity;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.entity.Theme;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 丁怡凡
 */
public interface ThemeDao {
    int[] batchInsert(List<Theme> themeList) throws SQLException;
    Theme findThemeByThname(String thname) throws SQLException;
    List<Entity> selectAll() throws SQLException;
    int insert(Theme theme) throws SQLException;

}
