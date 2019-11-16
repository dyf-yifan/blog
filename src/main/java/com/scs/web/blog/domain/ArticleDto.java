package com.scs.web.blog.domain;

import lombok.Data;

/**
 * @ClassName ArticleDto
 * @Description TODO
 * @Author ding
 * @Date 2019/11/10 12:01
 * @Version 1.0
 **/
@Data
public class ArticleDto {
    private String title;
    private String content;
    public ArticleDto(String title, String content){
        this.title = title;
        this.content = content;
    }
    public ArticleDto(){

    }
}
