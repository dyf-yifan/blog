package com.scs.web.blog.domain.vo;

import com.scs.web.blog.entity.Theme;
import com.scs.web.blog.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ThemeVo
 * @Description 专题视图类，包含专题自身信息，专题下所有文章，创建者信息（简略），关注着信息（简略）
 * @Author ding
 * @Date 2019/11/22 20:30
 * @Version 1.0
 **/
@Data
public class ThemeVo {
//    private Long id;
//    private String thName;
//    private String pic;
//    private Integer attention;
//    private Integer thNumber;
//    private String thTime;
//    private String look;
//    private String production;
//    private List<ArticleVo> articleList;
//    private SimpleUser simpleUser;
//    private List<SimpleUser> simpleUsers;
    private Theme theme;
    private User admin;
    private List<ArticleVo> articleList;
    private List<User> followList;
}
