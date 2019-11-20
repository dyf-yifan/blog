package com.scs.web.blog.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName Province
 * @Description TODO
 * @Author ding
 * @Date 2019/11/16 19:14
 * @Version 1.0
 **/
@Data
public class Province {
    private String name;
    private String level;
    private String code;
    private List<City> cities;
}
