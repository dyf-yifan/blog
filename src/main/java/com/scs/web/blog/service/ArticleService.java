package com.scs.web.blog.service;

import com.scs.web.blog.util.Result;

/**
 * @author 丁怡凡
 */
public interface ArticleService {
    /**
     * 获取热门文章
     *
     * @return
     */
    Result getHotArticles();

    /**
     * 获取全部文章
     *
     * @return
     */
    Result getArticles();
    /**
     *获取分页文章
     * @param currentPage
     * @param count
     * @return
     */
    Result getArticlesByPage(int currentPage, int count);

    /**
     * 获取文章详情
     *
     * @param id
     * @return
     */
    Result getArticle(long id);

    /**
     * 根据标题或摘要模糊查询文章
     *
     * @param keywords
     * @return
     */
    Result selectByKeywords(String keywords);

}
