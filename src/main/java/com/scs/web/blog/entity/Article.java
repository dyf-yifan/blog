package com.scs.web.blog.entity;

import lombok.Data;

/**
 * @ClassName Article
 * @Description TODO
 * @Author ding
 * @Date 2019/11/10 10:26
 * @Version 1.0
 **/

@Data
public class Article {
    private Long id;
    private String title;
    private String content;
    private String cover;
    private Integer diamond;
    private String nickname;
    private Integer comments;
    private Integer likes;
    private String publish_time;

}
