package com.scs.web.blog.domain;

/**
 * @ClassName ArticleDto
 * @Description TODO
 * @Author ding
 * @Date 2019/11/10 12:01
 * @Version 1.0
 **/
public class ArticleDto {
    private String title;
    private String content;
    public ArticleDto(String title, String content){
        this.title = title;
        this.content = content;
    }
    public ArticleDto(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
