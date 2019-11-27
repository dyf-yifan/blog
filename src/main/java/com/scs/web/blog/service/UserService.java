package com.scs.web.blog.service;

import com.scs.web.blog.domain.dto.UserDto;
import com.scs.web.blog.domain.vo.UserVo;
import com.scs.web.blog.util.Result;

/**
 * 用户业务逻辑接口
 * @author 丁怡凡
 */
public interface UserService {
    /**
     * 用户登录功能
     * @param userDto
     * @return
     */
    Result signIn(UserDto userDto);
    /**
     * 获取热门用户信息
     * @return
     */
    Result getHotUsers();
    /**
     * 获取分页用户信息
     * @param currPage
     * @param count
     * @return
     */
    Result selectByPage(int currPage,int count);

    /**
     * 根据id查询用户详情数据
     * @param id
     * @return
     */
    UserVo getUser(long id);

    /**
     * 根据昵称或简介模糊搜索用户
     * @param keywords
     * @return
     */
    Result selectByKeywords(String keywords);
}
