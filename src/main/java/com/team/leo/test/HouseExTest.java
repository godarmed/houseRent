package com.team.leo.test;
import com.team.leo.service.house.HouseService;
import com.team.leo.service.houseEx.HouseExService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class HouseExTest {
    @Autowired
    private HouseExService houseExService;

    @Autowired
    private HouseService houseService;

    @Test
    public void selectHouseExAllTest(){
        System.out.println(houseExService.selectByPrimaryKey("1016"));
    }

    @Test
    public void selectHouseAllTest(){
        System.out.println(houseService.selectByPrimaryKey("1561386276806"));
    }
}