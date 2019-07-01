package com.team.leo.service.street;

import com.github.pagehelper.PageInfo;
import com.team.leo.entity.Street;
import com.team.leo.util.StreetParams;

import java.util.List;

public interface StreetService{
    int deleteByPrimaryKey(Integer id);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(Street record);

    int insertSelective(Street record);

    Street selectByPrimaryKey(Integer id);

    PageInfo<Street> selectAllStreet(StreetParams params);

    int updateByPrimaryKeySelective(Street record);

    int updateByPrimaryKey(Street record);
}
