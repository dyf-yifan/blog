package com.scs.web.blog.service;

import com.scs.web.blog.domain.UserDto;
import com.scs.web.blog.factory.ServiceFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserServiceTest {
    private UserService userService = ServiceFactory.getUserServiceInstance();
    @Test
    public void signIn() {
        Map<String,Object> map = new HashMap<>();
        UserDto userDao = new UserDto("13937032620","f25df802e1f2a23eec2c7901b72d290d");
        map = userService.signIn(userDao);
        System.out.println(map.get("msg"));
        System.out.println(map.get("data"));
    }
}