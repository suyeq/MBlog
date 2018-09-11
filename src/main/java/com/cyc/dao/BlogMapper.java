package com.cyc.dao;

import com.cyc.pojo.Blog;

import java.util.List;

public interface BlogMapper
{
    /**
     * 获得所有微博
     * 只取出 blogid
     **/
    List<Blog> selectAll();

    /**
     * 获得一个用户所属的所有微博
     * 只取出 blogid
     **/
    List<Blog> selectByUserId(Integer userid);

    int deleteByPrimaryKey(Integer blogid);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer blogid);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKeyWithBLOBs(Blog record);

    int updateByPrimaryKey(Blog record);
}