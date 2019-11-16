package com.scs.web.blog.factory;

import com.scs.web.blog.entity.Article;
import com.scs.web.blog.service.ArticleService;
import com.scs.web.blog.service.ThemeService;
import com.scs.web.blog.service.UserService;
import com.scs.web.blog.service.impl.ArticleServiceImpl;
import com.scs.web.blog.service.impl.ThemeServiceImpl;
import com.scs.web.blog.service.impl.UserServiceImpl;

/**
 * @ClassName ServiceFoctory
 * @Description TODO
 * @Author
 * @Date 2019/11/9 16:40
 * @Version 1.0
 **/
public class ServiceFactory {
    public static UserService getUserServiceInstance() {
            return new UserServiceImpl();
    }
    public static ArticleService getArticleServiceInstance(){
        return new ArticleServiceImpl();
    }
    public static ThemeService getThemeServiceInstance() {
        return new ThemeServiceImpl();
    }
}
