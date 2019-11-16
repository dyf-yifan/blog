package com.scs.web.blog.dao;

import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.util.JsoupSpiderThree;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ThemeDaoTest {
    private static Logger logger = (Logger) LoggerFactory.getLogger(ThemeDaoTest.class);
    private ThemeDao themeDao = DaoFactory.getThemeDaoInstance();
    @Test
    public void batchInsert() {
        try{
            int[] result = themeDao.batchInsert((JsoupSpiderThree.getThemes()));
        }catch (SQLException e){
            logger.error("批量新增图书出现异常");
                e.printStackTrace();
        }
    }

    @Test
    public void selectAll() {

    }
}