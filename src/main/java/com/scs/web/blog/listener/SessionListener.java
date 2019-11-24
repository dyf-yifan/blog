package com.scs.web.blog.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @ClassName SessionListener
 * @Description Session监听器
 * @Author ding
 * @Date 2019/11/21 8:38
 * @Version 1.0
 **/
@WebListener
public class SessionListener implements HttpSessionListener {
    private MySessionContext myc = MySessionContext.getInstance();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        myc.addSession(session);
        System.out.println("新的session创建：" + session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        myc.delSession(session);
    }
}
