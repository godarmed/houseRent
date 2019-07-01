package com.team.leo.service.house;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.leo.entity.House;
import com.team.leo.entity.HouseExample;
import com.team.leo.mapper.HouseMapper;
import com.team.leo.util.HouseParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class HouseServiceImpl implements HouseService{
    @Autowired
    private HouseMapper houseMapper;
    
    @Override
    public int deleteByPrimaryKey(String id) {
        List<String> ids = new ArrayList<>();
        ids.add(id);
        return houseMapper.deleteByPrimaryKey(ids);
    }

    @Override
    public int deleteByPrimaryKey(List<String> ids) {
        return houseMapper.deleteByPrimaryKey(ids);
    }

    @Override
    public int insert(House record) {
        return houseMapper.insert(record);
    }

    @Override
    public int insertSelective(House record) {
        return houseMapper.insertSelective(record);
    }

    @Override
    public House selectByPrimaryKey(String id) {
        return houseMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<House> selectAllHouse(HouseParams params) {
        //添加查询条件
        HouseExample houseExample = new HouseExample();
        HouseExample.Criteria criteria = houseExample.createCriteria();
        if(params.getUserId() != null) {
            criteria.andUserIdEqualTo(params.getUserId());
        }
        if(params.getIsdel() != null){
            criteria.andIsdelEqualTo(params.getIsdel());
        }
        if(params.getIspass() != null){
            criteria.andIsdelEqualTo(params.getIspass());
        }

        //添加分页条件
        PageHelper.startPage(params.getPage(),params.getRows());
        List<House> houseList = houseMapper.selectByExample(houseExample);
        PageInfo<House> pageInfo = new PageInfo<>(houseList);
        return pageInfo;
    }

    @Override
    public int updateByPrimaryKeySelective(House record) {
        return houseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(House record) {
        return houseMapper.updateByPrimaryKey(record);
    }
}
