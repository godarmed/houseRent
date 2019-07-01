package com.team.leo.mapper;

import com.team.leo.entity.Users;
import com.team.leo.entity.UsersExample;
import com.team.leo.util.UsersParams;

import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(List<Integer> ids);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    List<Users> selectAllUsers(Integer page, Integer rows);

    List<Users> selectUsers(UsersParams params);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    int countByExample(UsersExample example);

    int deleteByPrimaryKey(Integer id);

    List<Users> selectByExample(UsersExample example);
}