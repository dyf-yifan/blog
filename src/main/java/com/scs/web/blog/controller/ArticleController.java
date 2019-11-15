package com.scs.web.blog.controller;

import cn.hutool.db.Entity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.domain.UserDto;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.service.UserService;
import com.scs.web.blog.util.Message;
import com.scs.web.blog.util.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ArticleController
 * @Description 文章的控制层
 * @Author ding
 * @Date 2019/11/10 11:33
 * @Version 1.0
 **/
@WebServlet(urlPatterns = "/api/article")
public class ArticleController extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService = ServiceFactory.getUserServiceInstance();
    private ArticleDao ArticleController = DaoFactory.getArticleDaoInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        logger.info("登录用户信息：" + stringBuilder.toString());
        Gson gson = new GsonBuilder().create();
        UserDto userDto = gson.fromJson(stringBuilder.toString(), UserDto.class);
        Map<String, Object> map = userService.signIn(userDto);
        String msg = (String) map.get("msg");
        ResponseObject ro;
        if(msg.equals(Message.SIGN_IN_SUCCESS)){
            ro = ResponseObject.success(200,msg,map.get("data"));
        }else{
            ro = ResponseObject.success(200,msg);
        }
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();
    }
        @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Entity> entityList = null;
        try{
            entityList = DaoFactory.getArticleDaoInstance().selectAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
//        设置响应对象的内容类型
        resp.setContentType("application/json;charset = UTF-8");
        PrintWriter out = resp.getWriter();
        Integer code = null;
        String msg = null;
        Object user = null;
        ResponseObject ro =  ResponseObject.success(code,msg,user);
        ro.setCode(resp.getStatus());
      ro.setMsg("成功");
        ro.setData(entityList);
//        创建一个Gson对象
        Gson json = new GsonBuilder().create();
        out.print(json.toJson(ro));
        out.close();
    }
    private void selectAll(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        List<Entity> entityList = null;
        try {
            entityList = DaoFactory.getArticleDaoInstance().selectAll();
        } catch (SQLException e) {
            System.err.println("查询文章操作出现异常");
        }
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        Gson gson = new GsonBuilder().create();
        out.print(gson.toJson(entityList));
        out.close();
    }

}