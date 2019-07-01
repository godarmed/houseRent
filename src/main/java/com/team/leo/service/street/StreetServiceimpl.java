package com.team.leo.service.street;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.leo.entity.Street;
import com.team.leo.mapper.StreetMapper;
import com.team.leo.util.StreetParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StreetServiceimpl implements StreetService {
    @Autowired
    private StreetMapper streetMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        return streetMapper.deleteByPrimaryKey(ids);
    }

    @Override
    public int deleteByPrimaryKey(List<Integer> ids) {
        return streetMapper.deleteByPrimaryKey(ids);
    }

    @Override
    public int insert(Street record) {
        return streetMapper.insert(record);
    }

    @Override
    public int insertSelective(Street record) {
        return streetMapper.insertSelective(record);
    }

    @Override
    public Street selectByPrimaryKey(Integer id) {
        return streetMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Street> selectAllStreet(StreetParams params){
        PageHelper.startPage(params.getPage(),params.getRows());
        List<Street> streetList = streetMapper.selectAllStreet(params);
        PageInfo<Street> pageInfo = new PageInfo<>(streetList);
        return pageInfo;
    }

    @Override
    public int updateByPrimaryKeySelective(Street record) {
        return streetMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Street record) {
        return streetMapper.updateByPrimaryKey(record);
    }
}
