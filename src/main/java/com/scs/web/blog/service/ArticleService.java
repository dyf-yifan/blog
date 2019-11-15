package com.scs.web.blog.service;

import com.scs.web.blog.domain.ArticleDto;
import java.util.Map;

/**
 * @author 丁怡凡
 */
public interface ArticleService {
    Map<String,Object> signIn(ArticleDto articleDto);

}
