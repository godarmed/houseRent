package com.team.leo.test;

import com.team.leo.service.users.UsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class UsersTest {
    @Autowired
    private UsersService usersService;

    @Test
    public void selectUsersAllTest(){
        System.out.println(usersService.selectByPrimaryKey(60));
    }
}
