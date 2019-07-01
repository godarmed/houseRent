package com.team.leo.service.users;

import com.github.pagehelper.PageInfo;
import com.team.leo.entity.Users;
import com.team.leo.entity.UsersExample;
import com.team.leo.util.UsersParams;

import java.util.List;

public interface UsersService{
    boolean isUserExist(String userName);

    Users checkUser(Users user);

    int deleteByPrimaryKey(Integer id);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    List<Users> selectByExample(UsersExample example);

    PageInfo<Users> selectAllUsers(Integer page, Integer rows);

    PageInfo<Users> selectAllUsers(UsersParams params);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);


}
