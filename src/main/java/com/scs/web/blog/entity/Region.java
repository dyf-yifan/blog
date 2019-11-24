package com.scs.web.blog.entity;

import lombok.Data;

/**
 * @ClassName Region
 * @Description TODO
 * @Author ding
 * @Date 2019/11/24 10:59
 * @Version 1.0
 **/
@Data
public class Region {
    private  Integer id;
    private String name;
    private Integer parentId;
    private Integer level;
    private String cityCode;
    private String postCode;
    private String mergeName;
}
