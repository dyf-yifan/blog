package com.scs.web.blog.service;

import com.scs.web.blog.domain.ThemeDto;
import java.util.Map;

public interface ThemeService {
    Map<String,Object> signIn(ThemeDto themeDto);
}
