package com.scs.web.blog.controller;

import com.scs.web.blog.verify.ImageUtil;
import com.scs.web.blog.verify.StringUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName CodeController
 * @Description TODO
 * @Author ding
 * @Date 2019/11/20 11:34
 * @Version 1.0
 **/
@WebServlet(urlPatterns = "/api/code")
public class CodeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取随机验证码
        String code = StringUtil.getRandomString();
        //存入session
        HttpSession session = req.getSession();
        session.setAttribute("Access-Token",code);
        BufferedImage img = ImageUtil.getImage(200,100,code);
        //设置resp的响应内容类型
        resp.setContentType("image/jpg");
        OutputStream out = resp.getOutputStream();
        ImageIO.write(img,"jpg",out);
        out.close();
    }
}
