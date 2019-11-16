package com.scs.web.blog.service;

import com.scs.web.blog.domain.UserDto;
import java.util.Map;

/**
 * 用户登录功能
 * @author 丁怡凡
 */
public interface UserService {
    Map<String,Object> signIn(UserDto userDto);
}
