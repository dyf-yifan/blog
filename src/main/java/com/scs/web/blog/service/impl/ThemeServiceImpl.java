package com.scs.web.blog.service.impl;

import com.scs.web.blog.dao.ThemeDao;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.service.ThemeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

}
