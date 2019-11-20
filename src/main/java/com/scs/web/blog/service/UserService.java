package com.scs.web.blog.service;

import com.scs.web.blog.domain.UserDto;

import java.util.Map;

/**
 * 用户登录功能
 * @author 丁怡凡
 */
public interface UserService {
    /**
     * sign-in
     * @param userDto
     * @return
     */
    Map<String,Object> signIn(UserDto userDto);
    /**
     * 分页获取用户信息
     * @return
     */
//    List<User> getUsersPage(int currPage, int pageSize);
}
