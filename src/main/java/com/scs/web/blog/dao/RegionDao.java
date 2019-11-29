package com.scs.web.blog.dao;

import com.scs.web.blog.entity.Region;

import java.sql.SQLException;
import java.util.List;

public interface RegionDao {
    /**
     * 查询所有地址
     * @return
     * @throws SQLException
     */
    List<Region> selectAll()throws SQLException;
}
