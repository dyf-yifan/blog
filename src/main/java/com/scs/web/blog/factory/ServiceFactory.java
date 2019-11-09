package com.scs.web.blog.factory;

import com.scs.web.blog.service.UserService;
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
}
