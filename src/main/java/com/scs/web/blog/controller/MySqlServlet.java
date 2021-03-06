package com.scs.web.blog.controller;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * @ClassName MySqlServlet
 * @Description TODO
 * @Author ding
 * @Date 2019/11/24 9:34
 * @Version 1.0
 **/
@WebServlet(urlPatterns = "/data")
public class MySqlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context initCtx = null;
        try {
            initCtx = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        Context envCtx = null;
        try {
            envCtx = (Context) initCtx.lookup("java:comp/env");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        assert envCtx != null;
        DataSource dataSource = null;
        try {
            dataSource = (DataSource) envCtx.lookup("jdbc/datasource");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        System.out.println(dataSource);
    }
}
