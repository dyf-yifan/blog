package com.scs.web.blog.service;

import com.scs.web.blog.domain.vo.ArticleVo;

import java.util.List;

/**
 * @author 丁怡凡
 */
public interface ArticleService {
    /**
     * 获取热门文章
     * @return
     */
    List<ArticleVo> getHotArticles();
}
