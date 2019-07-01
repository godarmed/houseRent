package com.team.leo.mapper;

import com.team.leo.entity.Type;
import com.team.leo.entity.TypeExample;
import java.util.List;

public interface TypeMapper {
    int countByExample(TypeExample example);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(Type record);

    int insertSelective(Type record);

    List<Type> selectByExample(TypeExample example);

    Type selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);
}