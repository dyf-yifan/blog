package com.scs.web.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Article
 * @Descriptio 文章实体类
 * @Author ding
 * @Date 2019/11/10 10:26
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private Long id;
    private String title;
    private String content;
    private String cover;
    private Integer diamond;
    private Integer comments;
    private Integer likes;
    private String publishTime;
    private Long userId;
    private String unlikes;
    private Long themeId;
}
