package com.scs.web.blog.service;

import com.scs.web.blog.util.Result;

/**
 * @author 丁怡凡
 */
public interface ThemeService {
    /**
     * 获取热门专题
     * @return
     */
    Result getHotThemes();


    /**
     * 根据id获取专题详情
     * @param id
     * @return
     */
    Result getTheme(long id);

    /**
     * 获取所有专题
     * @return
     */
    Result getThemes();

    /**
     * 根据名称或描述模糊搜索专题
     *
     * @param keywords
     * @return
     */
    Result selectByKeywords(String keywords);


    /**
     * 分页查询专题信息
     * @param currentPage
     * @param count
     * @return
     */
    Result selectByPage(int currentPage, int count);

}
