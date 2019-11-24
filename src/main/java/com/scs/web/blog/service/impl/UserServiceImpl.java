package com.scs.web.blog.service.impl;

import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.dao.UserDao;
import com.scs.web.blog.domain.dto.UserDto;
import com.scs.web.blog.domain.vo.ArticleVo;
import com.scs.web.blog.domain.vo.UserVo;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.service.UserService;
import com.scs.web.blog.util.Result;
import com.scs.web.blog.util.ResultCode;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description 用户业务逻辑接口实现类
 * @Author
 * @Date 2019/11/9 15:45
 * @Version 1.0
 **/
public class UserServiceImpl implements UserService {
    private UserDao userDao = DaoFactory.getUserDaoInstance();
    private ArticleDao articleDao = DaoFactory.getArticleDaoInstance();
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Result signIn(UserDto userDto) {
        User user = null;
        try {
            user = userDao.findUserByMobile(userDto.getMobile());

        } catch (SQLException e) {
            logger.error("根据手机号查询用户出现异常");
        }
        if(user != null){
            if(user.getPassword().equals(DigestUtils.md5Hex(userDto.getPassword()))){
                return Result.success(user);
            }else {
                return Result.failure(ResultCode.USER_PASSWORD_ERROR);
            }
        }else {
            return Result.failure(ResultCode.USER_ACCOUNT_ERROR);
        }
    }

    @Override
    public Result getHotUsers() {
        List<User> userList = null;
        try {
            userList = userDao.selectHotUsers();
        } catch (SQLException e){
            logger.error("获取热门用户出现异常");
        }
        if (userList != null) {
            return Result.success(userList);
        }else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }


    @Override
    public Result selectByPage(int currentPage, int count) {
        List<User> userList = null;
        try {
            userList = userDao.selectByPage(currentPage,count);
        }catch (SQLException e){
            logger.error("分页查询用户出现异常");
        }
        if (userList != null) {
            return Result.success(userList);
        }else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public Result getUser(long id) {
        UserVo userVo = null;
        try {
            userVo = userDao.getUser(id);
        } catch (SQLException e) {
            logger.error("根据id获取用户详情出现异常");
        }
        if (userVo != null) {
            try {
                List<ArticleVo> articleVoList = articleDao.selectByUserId(id);
            } catch (SQLException e) {
                logger.error("根据用户id获取列表文章数据出现异常");
            }
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }

    @Override
    public Result selectByKeywords(String keywords) {
        List<User> userList = null;
        try {
            userList = userDao.selectByKeywords(keywords);
        }catch (SQLException e){
            logger.error("根据关键字查询用户出现异常");
        }
        if (userList != null){
            return Result.success(userList);
        }else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }
}
