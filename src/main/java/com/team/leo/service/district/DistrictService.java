package com.team.leo.service.district;

import com.github.pagehelper.PageInfo;
import com.team.leo.entity.District;
import com.team.leo.util.DistrictParams;

import java.util.List;

public interface DistrictService {
    int deleteByPrimaryKey(Integer id);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(District record);

    int insertSelective(District record);

    District selectByPrimaryKey(Integer id);

    PageInfo<District> selectDistrict(DistrictParams params);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);
}
