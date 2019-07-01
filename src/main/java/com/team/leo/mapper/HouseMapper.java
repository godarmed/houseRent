package com.team.leo.mapper;

import com.team.leo.entity.House;
import com.team.leo.entity.HouseExample;
import java.util.List;

public interface HouseMapper {
    int countByExample(HouseExample example);

    int deleteByPrimaryKey(List<String> ids);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);
}