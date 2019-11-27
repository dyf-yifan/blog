package com.scs.web.blog.dao.impl;

import com.scs.web.blog.domain.vo.UserVo;
import org.junit.Test;

public class UserDaoImplTest {

    @Test
    public void getUser() {
        UserDaoImpl a  = new UserDaoImpl();
        UserVo uservo = a.getUser(1);
    }
}