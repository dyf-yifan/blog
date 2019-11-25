package com.scs.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.service.ThemeService;
import com.scs.web.blog.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName ThemeController
 * @Description 专题控制器
 * @Author ding
 * @Date 2019/11/16 11:59
 * @Version 1.0
 **/
@WebServlet(urlPatterns = {"/api/theme","/api/theme/*"})
public class ThemeController extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(ThemeController.class);
    private ThemeService themeService = ServiceFactory.getThemeServiceInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        if ("/api/theme".equals(uri)) {
            String page = req.getParameter("page");
            String keywords = req.getParameter("keywords");
            String count = req.getParameter("count");
            if (page != null) {
                getThemesByPage(resp, Integer.parseInt(page), Integer.parseInt(count));
            } else if (keywords != null) {
                getThemesByKeywords(resp, keywords);
            } else {
                getHotThemes(req, resp);
            }
        } else {
            getTheme(req, resp);
        }
    }

    private void getHotThemes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        Result result = themeService.getHotThemes();
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getThemesByPage(HttpServletResponse resp, int page, int count) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        Result result = themeService.selectByPage(page, count);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getThemesByKeywords(HttpServletResponse resp, String keywords) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        Result result = themeService.selectByKeywords(keywords);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getTheme(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String info = req.getPathInfo().trim();
//        取得路径参数
        String id = info.substring(info.indexOf("/") + 1);
        Gson gson = new GsonBuilder().create();
        Result result = themeService.getTheme(Long.parseLong(id));
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }
}
