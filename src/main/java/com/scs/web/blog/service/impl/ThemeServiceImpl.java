package com.scs.web.blog.service.impl;

import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.dao.ThemeDao;
import com.scs.web.blog.domain.vo.ArticleVo;
import com.scs.web.blog.domain.vo.ThemeVo;
import com.scs.web.blog.entity.Theme;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.service.ThemeService;
import com.scs.web.blog.util.Result;
import com.scs.web.blog.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ThemeServiceImpl
 * @Description TODO
 * @Author ding
 * @Date 2019/11/15 15:19
 * @Version 1.0
 **/
public class ThemeServiceImpl implements ThemeService {
    private ThemeDao themeDao = DaoFactory.getThemeDaoInstance();
    private ArticleDao articleDao = DaoFactory.getArticleDaoInstance();
    private static Logger logger = LoggerFactory.getLogger(ThemeServiceImpl.class);

    @Override
    public Result getHotThemes() {
        List<Theme> themeList = null;
        try {
            themeList = themeDao.selectHotThemes();
        } catch (SQLException e){
            logger.error("获取热门专题出现异常");
        }
        if (themeList != null) {
            return Result.success(themeList);
        }else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }


    @Override
    public Result getTheme(long id) {
        ThemeVo themeVo = null;
        try {
            themeVo = themeDao.getTheme(id);
        } catch (SQLException e) {
            logger.error("根据id获取专题详情出现异常");
        }
        if (themeVo != null) {
            try {
                List<ArticleVo> articleVoList = articleDao.selectByThemeId(themeVo.getTheme().getId());
                themeVo.setArticleList(articleVoList);
            } catch (SQLException e){
                logger.error("根据专题id获取所有文章出现异常");
            }
            return Result.success(themeVo);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public Result getThemes() {
        List<Theme> themeList = null;
        try {
            themeList = themeDao.findAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        if (themeList != null){
            return Result.success(themeList);
        }else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public Result selectByKeywords(String keywords) {
        List<Theme> themeList = null;
        try {
            themeList = themeDao.selectByKeywords(keywords);
        }catch (SQLException e){
            logger.error("根据关键字查询专题出现异常");
        }
        if (themeList != null){
            return Result.success(themeList);
        }else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public Result selectByPage(int currentPage, int count) {
        List<Theme> themeList = null;
        try {
            themeList = themeDao.selectByPage(currentPage,count);
        }catch (SQLException e){
            logger.error("分页查询专题出现异常");
        }
        if (themeList != null) {
            return Result.success(themeList);
        }else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }
}
