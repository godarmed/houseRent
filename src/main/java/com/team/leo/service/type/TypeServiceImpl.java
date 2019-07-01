package com.team.leo.service.type;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.leo.entity.Type;
import com.team.leo.entity.TypeExample;
import com.team.leo.mapper.TypeMapper;
import com.team.leo.util.TypeParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    
    @Autowired
    private TypeMapper typeMapper;
    
    @Override
    public int deleteByPrimaryKey(Integer id) {
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        return typeMapper.deleteByPrimaryKey(ids);
    }

    @Override
    public int deleteByPrimaryKey(List<Integer> ids) {
        return typeMapper.deleteByPrimaryKey(ids);
    }

    @Override
    public int insert(Type record) {
        return typeMapper.insert(record);
    }

    @Override
    public int insertSelective(Type record) {
        return typeMapper.insertSelective(record);
    }

    @Override
    public Type selectByPrimaryKey(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Type> selectAllType(TypeParams params) {
        //添加查询条件
        TypeExample typeExample = new TypeExample();
        TypeExample.Criteria criteria = typeExample.createCriteria();
        criteria.andNameLike("%"+params.getName()+"%");
        //添加分页条件
        PageHelper.startPage(params.getPage(),params.getRows());
        List<Type> typeList = typeMapper.selectByExample(typeExample);
        PageInfo<Type> pageInfo = new PageInfo<>(typeList);
        return pageInfo;
    }

    @Override
    public int updateByPrimaryKeySelective(Type record) {
        return typeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Type record) {
        return typeMapper.updateByPrimaryKey(record);
    }
}
