package com.team.leo.service.houseEx;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.leo.entity.HouseEx;
import com.team.leo.mapper.HouseExMapper;
import com.team.leo.util.HouseParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseExServiceImpl implements HouseExService {
    @Autowired
    private HouseExMapper houseExMapper;

    @Override
    public HouseEx selectByPrimaryKey(String id) {
        return houseExMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<HouseEx> selectByExample(HouseParams params) {
        //添加分页条件
        PageHelper.startPage(params.getPage(),params.getRows());
        List<HouseEx> houseExList = houseExMapper.selectByExample(params);
        PageInfo<HouseEx> pageInfo = new PageInfo<>(houseExList);
        return pageInfo;
    }
}
