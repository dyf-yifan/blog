package com.scs.web.blog.entity;

import lombok.Data;

/**
 * @ClassName Theme
 * @Description TODO
 * @Author ding
 * @Date 2019/11/15 11:03
 * @Version 1.0
 **/
@Data
public class Theme {
    private Long id;
    private String thname;
    private String pic;
    private Integer attention;
    private Integer thnumber;
    private String thtime;
    private String look;
    private String production;
}
