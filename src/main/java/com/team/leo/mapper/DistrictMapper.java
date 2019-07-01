package com.team.leo.mapper;

import com.team.leo.entity.District;
import com.team.leo.util.DistrictParams;

import java.util.List;

public interface DistrictMapper {
    int deleteByPrimaryKey(List<Integer> ids);

    int insert(District record);

    int insertSelective(District record);

    District selectByPrimaryKey(Integer id);

    List<District> selectDistricts(DistrictParams params);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);
}