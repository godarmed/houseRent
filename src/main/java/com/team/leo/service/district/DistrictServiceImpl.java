package com.team.leo.service.district;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.leo.entity.District;
import com.team.leo.mapper.DistrictMapper;
import com.team.leo.util.DistrictParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService{
    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        return deleteByPrimaryKey(ids);
    }

    @Override
    public int deleteByPrimaryKey(List<Integer> ids) {
        return districtMapper.deleteByPrimaryKey(ids);
    }

    @Override
    public int insert(District record) {
        return districtMapper.insert(record);
    }

    @Override
    public int insertSelective(District record) {
        return districtMapper.insertSelective(record);
    }

    @Override
    public District selectByPrimaryKey(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }



    @Override
    public PageInfo<District> selectDistrict(DistrictParams params) {
        PageHelper.startPage(params.getPage(),params.getRows());
        List<District> districtList = districtMapper.selectDistricts(params);
        PageInfo<District> pageInfo = new PageInfo<>(districtList);
        return pageInfo;
    }

    @Override
    public int updateByPrimaryKeySelective(District record) {
        return districtMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(District record) {
        return districtMapper.updateByPrimaryKey(record);
    }
}
