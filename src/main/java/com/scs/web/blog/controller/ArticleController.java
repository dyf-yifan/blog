package com.scs.web.blog.controller;

import cn.hutool.db.Entity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.util.ResponseObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ArticleController
 * @Description 文章的控制层
 * @Author ding
 * @Date 2019/11/10 11:33
 * @Version 1.0
 **/
@WebServlet(urlPatterns = "/article")
public class ArticleController extends HttpServlet {
    private ArticleDao ArticleController = DaoFactory.getArticleDaoInstance();

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
