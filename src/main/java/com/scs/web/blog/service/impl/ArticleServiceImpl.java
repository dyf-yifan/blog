package com.scs.web.blog.service.impl;

import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.domain.ArticleDto;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.service.ArticleService;
import com.scs.web.blog.util.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ArticleServiceImpl
 * @Description TODO
 * @Author ding
 * @Date 2019/11/10 12:07
 * @Version 1.0
 **/
public class ArticleServiceImpl implements ArticleService {
    private ArticleDao articleDao = DaoFactory.getArticleDaoInstance();
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public Map<String, Object> check(ArticleDto articleDto) {
        Article article = null;
        Map<String,Object> map = new HashMap<>();
        try{
            article = articleDao.findArticleByTitle(articleDto.getTitle());
        }catch (SQLException e){
            logger.error("根据书名查询图书出现异常");
        }
        if (article != null){
            if(article.getContent().equals(articleDto.getContent())){
                map.put("msg", Message.SIGN_IN_SUCCESS);
                map.put("data",article);
            }else{
                map.put("msg",Message.PASSWORD_ERROR);
            }
        }else {
            map.put("msg",Message.MOBILE_NOT_FOUND);
        }
        return map;
    }
}

