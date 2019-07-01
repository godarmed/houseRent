package com.team.leo.test;

import com.team.leo.service.Emp.EmpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class EmpTest {
    @Autowired
    private EmpService empService;

    @Test
    public void selectEmpAllTest(){
        System.out.println(empService.selectEmpAll(null));
    }
}
