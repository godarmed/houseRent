package com.team.leo.service.house;

import com.github.pagehelper.PageInfo;
import com.team.leo.entity.House;
import com.team.leo.util.HouseParams;

import java.util.List;

public interface HouseService {
    int deleteByPrimaryKey(String id);

    int deleteByPrimaryKey(List<String> ids);

    int insert(House record);

    int insertSelective(House record);

    House selectByPrimaryKey(String id);

    PageInfo<House> selectAllHouse(HouseParams params);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);
}
