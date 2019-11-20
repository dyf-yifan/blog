package com.scs.web.blog.controller;

import com.scs.web.blog.util.ImageUtil;
import com.scs.web.blog.util.Util;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@WebServlet(urlPatterns = "/code")
public class CodeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = Util.getRandomString(4);
        BufferedImage img = ImageUtil.getImage(code,200,100);
//        设置resp
        resp.setContentType("image/jpeg");
        OutputStream out = resp.getOutputStream();
        ImageIO.write(img,"jpg",out);
        out.close();
    }
}
