package com.scs.web.blog.domain.vo;

import com.scs.web.blog.entity.Article;
import com.scs.web.blog.entity.Theme;
import com.scs.web.blog.entity.User;
import lombok.Data;

/**
 * @ClassName ArticleVo
 * @Description 文章视图类，从文章，专题，用户表联查出前端需要展示的数据
 * @Author ding
 * @Date 2019/11/16 19:23
 * @Version 1.0
 **/
@Data
public class ArticleVo {
//    private Long id;
//    private String title;
//    private String content;
//    private String cover;
//    private Integer diamond;
//    private Integer comments;
//    private Integer likes;
//    private String publishTime;
//    private String userAvatar;
//    private Long userId;
//    private Long themeId;
//    private String unlikes;
//    private String nickname;
//    private String avatar;
//    private String thName;
//    private String pic;
    private Article article;
    private User author;
    private Theme theme;

}
