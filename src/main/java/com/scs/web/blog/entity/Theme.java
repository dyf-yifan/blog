package com.scs.web.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Theme
 * @Description 专题实体类
 * @Author ding
 * @Date 2019/11/15 11:03
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Theme {
    private Long id;
    private String thName;
    private String pic;
    private Integer attention;
    private Integer thNumber;
    private String thTime;
    private String look;
    private String production;
    private Long adminId;
    private String homepage;
}
