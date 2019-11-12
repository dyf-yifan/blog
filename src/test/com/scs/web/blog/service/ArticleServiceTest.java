package com.scs.web.blog.service;

import com.scs.web.blog.domain.ArticleDto;
import com.scs.web.blog.domain.UserDto;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.ServiceFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ArticleServiceTest {
    private UserService articleService = ServiceFactory.getUserServiceInstance();

    @Test
    public void signIn() {
        Map<String,Object> map = new HashMap<>();
        UserDto userDao = new UserDto("139713068","2e4cfa2abe0d344706566c928ea843");
        map = articleService.signIn(userDao);
        System.out.println(map.get("msg"));
        System.out.println(map.get("data"));
    }
}