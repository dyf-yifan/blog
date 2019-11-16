package com.scs.web.blog.service.impl;

import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.dao.ThemeDao;
import com.scs.web.blog.domain.ArticleDto;
import com.scs.web.blog.domain.ThemeDto;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.entity.Theme;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.service.ThemeService;
import com.scs.web.blog.util.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ThemeServiceImpl
 * @Description TODO
 * @Author ding
 * @Date 2019/11/15 15:19
 * @Version 1.0
 **/
public class ThemeServiceImpl implements ThemeService {
    private ThemeDao themeDao = DaoFactory.getThemeDaoInstance();
    private static Logger logger = LoggerFactory.getLogger(ThemeServiceImpl.class);
    @Override
    public Map<String, Object> signIn(ThemeDto themeDto) {
        Theme theme = null;
        Map<String,Object> map = new HashMap<>();
        try{
            theme = themeDao.findThemeByThname(themeDto.getThname());
        }catch (SQLException e){
            logger.error("根据书名查询图书出现异常");
        }
        if (theme != null){
            if(theme.getProduction().equals(themeDto.getProduction())){
                map.put("msg", Message.SIGN_IN_SUCCESS);
                map.put("data",theme);
            }else{
                map.put("msg",Message.PASSWORD_ERROR);
            }
        }else {
            map.put("msg",Message.MOBILE_NOT_FOUND);
        }
        return map;
    }
}
