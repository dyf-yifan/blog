package com.scs.web.blog.domain.vo;

import lombok.Data;

/**
 * @ClassName Articlevo
 * @Description TODO
 * @Author ding
 * @Date 2019/11/16 19:23
 * @Version 1.0
 **/
@Data
public class ArticleVo {
    private Long id;
    private Integer userid;
    private String title;
    private String cover;
    private String nickname;
    private String content;
    private String likes;
    private String unlikes;
    private String useravatar;
    private Integer diamond;
}
