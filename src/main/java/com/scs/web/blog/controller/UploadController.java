package com.scs.web.blog.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName UploadController
 * @Description 上传文件
 * @Author ding
 * @Date 2019/11/21 10:19
 * @Version 1.0
 **/
@MultipartConfig
@WebServlet(urlPatterns = "/upload")
public class UploadController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Part> parts = req.getParts();

        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date(current));
        System.out.println(time);
        String path = req.getSession().getServletContext().getRealPath("/")+time;
        System.out.println(path);
        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();
        }

        for (Part part : parts){
            long max = 1024*1024*1;
            if (part.getSize()<max){
                String contentType = part.getContentType();
                String fileName = part.getSubmittedFileName();
                String fileExtName = fileName.substring(fileName.lastIndexOf(".") - 1);
                part.write(f.getPath()+"/"+ UUID.randomUUID().toString()+fileExtName);
                req.setAttribute("msg","上传成功");

            }else {
                req.setAttribute("msg","上传失败");
            }

        }
        resp.setContentType("image/jpeg");
//        req.setAttribute("url","http://localhost:8080/"+time+"/"+fileName);
        req.getRequestDispatcher("/upload.jsp").forward(req,resp);
    }
}
