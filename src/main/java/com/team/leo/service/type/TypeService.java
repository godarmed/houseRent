package com.team.leo.service.type;

import com.github.pagehelper.PageInfo;
import com.team.leo.entity.Type;
import com.team.leo.util.TypeParams;

import java.util.List;

public interface TypeService {
    int deleteByPrimaryKey(Integer id);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Integer id);

    PageInfo<Type> selectAllType(TypeParams params);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);
}
