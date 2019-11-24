package com.scs.web.blog.domain.vo;

import com.scs.web.blog.entity.Theme;
import com.scs.web.blog.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @ClassName UserVo
 * @Description 用户视图类，包括用户自身信息，发表的所有文章，所有粉丝和关注的人
 * @Author ding
 * @Date 2019/11/22 20:29
 * @Version 1.0
 **/
@Data
public class UserVo {
//    private Long id;
//    private String mobile;
//    private String password;
//    private String nickname;
//    private String avatar;
//    private String gender;
//    private LocalDate birthday;
//    private String address;
//    private String introduction;
//    private String homepage;
//    private Short follows;
//    private Short fans;
//    private Short articles;
//    private LocalDateTime createTime;
//    private Short status;
//    private Long fNumber;
//    private Long achieveLike;
//    private Long total;
//    private String banner;
//    private String email;
//    private List<Article> articleList;
//    private List<SimpleUser> simpleUserList;
    private User user;
    private List<ArticleVo> articleList;
    private List<Theme> themeList;
    private List<User> fansList;
}
