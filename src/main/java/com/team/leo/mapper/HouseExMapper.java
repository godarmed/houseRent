package com.team.leo.mapper;

import com.team.leo.entity.HouseEx;
import com.team.leo.util.HouseParams;

import java.util.List;

public interface HouseExMapper {

    HouseEx selectByPrimaryKey(String userId);

    List<HouseEx> selectByExample(HouseParams params);

}
