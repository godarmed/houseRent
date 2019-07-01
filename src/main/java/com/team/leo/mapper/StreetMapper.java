package com.team.leo.mapper;

import com.github.pagehelper.PageInfo;
import com.team.leo.entity.Street;
import com.team.leo.util.StreetParams;

import java.util.List;

public interface StreetMapper {
    int deleteByPrimaryKey(List<Integer> ids);

    int insert(Street record);

    int insertSelective(Street record);

    Street selectByPrimaryKey(Integer id);

    List<Street> selectAllStreet(StreetParams params);

    int updateByPrimaryKeySelective(Street record);

    int updateByPrimaryKey(Street record);
}