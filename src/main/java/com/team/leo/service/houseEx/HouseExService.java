package com.team.leo.service.houseEx;

import com.github.pagehelper.PageInfo;
import com.team.leo.entity.HouseEx;
import com.team.leo.util.HouseParams;

public interface HouseExService {
    HouseEx selectByPrimaryKey(String id);
    PageInfo<HouseEx> selectByExample(HouseParams params);
}
