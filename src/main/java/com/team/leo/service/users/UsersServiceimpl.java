package com.team.leo.service.users;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.leo.entity.Users;
import com.team.leo.entity.UsersExample;
import com.team.leo.mapper.UsersMapper;
import com.team.leo.util.MD5Utils;
import com.team.leo.util.UsersParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceimpl implements UsersService{
    @Autowired
    private UsersMapper usersMapper;

    /**
     * 检查用户是否存在
     * @param userName
     * @return
     */
    @Override
    public boolean isUserExist(String userName) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(userName);
        criteria.andIsadminEqualTo(0);
        List<Users> users = usersMapper.selectByExample(usersExample);
        return users.size()==0?false:true;
    }

    /**
     * 检查用户是否存在
     * @param user
     * @return
     */
    @Override
    public Users checkUser(Users user) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(user.getName());
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(user.getPassword()));
        List<Users> users = usersMapper.selectByExample(usersExample);
        return users.size() == 0?null:users.get(0);
    }


    /**
     * 按照id删除单个用户
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        return usersMapper.deleteByPrimaryKey(ids);
    }

    /**
     * 按照id批量删除用户
     * @param ids
     * @return
     */
    @Override
    public int deleteByPrimaryKey(List<Integer> ids) {
        return usersMapper.deleteByPrimaryKey(ids);
    }

    /**
     * 添加用户
     * @param record
     * @return
     */
    @Override
    public int insert(Users record) {
        //密码加密
        record.setPassword(MD5Utils.md5Encrypt(record.getPassword()));
        return usersMapper.insert(record);
    }

    /**
     * 添加用户
     * @param record
     * @return
     */
    @Override
    public int insertSelective(Users record) {
        return usersMapper.insertSelective(record);
    }

    /**
     * 按照id查找用户
     * @param id
     * @return
     */
    @Override
    public Users selectByPrimaryKey(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }

    /**
     * 默认条件查询
     * @param example
     * @return
     */
    @Override
    public List<Users> selectByExample(UsersExample example) {
        return usersMapper.selectByExample(example);
    }

    /**
     * 查找全部用户
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageInfo<Users> selectAllUsers(Integer page, Integer rows){
        PageHelper.startPage(page,rows);
        List<Users> usersList = usersMapper.selectAllUsers(page,rows);
        PageInfo<Users> pageInfo = new PageInfo<>(usersList);
        return pageInfo;
    }

    /**
     * 按UsersParams查找用户
     * @param params
     * @return
     */
    @Override
    public PageInfo<Users> selectAllUsers(UsersParams params){
        PageHelper.startPage(params.getPage(),params.getRows());
        List<Users> usersList = usersMapper.selectUsers(params);
        PageInfo<Users> pageInfo = new PageInfo<>(usersList);
        return pageInfo;
    }

    /**
     * 修改用户
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(Users record) {
        return usersMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 修改用户
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKey(Users record) {
        return usersMapper.updateByPrimaryKey(record);
    }
}
