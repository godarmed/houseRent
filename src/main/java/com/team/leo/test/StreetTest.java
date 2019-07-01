package com.team.leo.test;

import com.team.leo.service.district.DistrictService;
import com.team.leo.service.street.StreetService;
import com.team.leo.util.DistrictParams;
import com.team.leo.util.StreetParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class StreetTest {
    @Autowired
    private StreetService streetService;

    @Autowired
    private DistrictService districtService;

    @Test
    public void selectEmpAllTest(){
        System.out.println(streetService.selectAllStreet(new StreetParams(1,5)));
    }

    @Test
    public void selectDistrictAllTest(){
        System.out.println(districtService.selectDistrict(new DistrictParams(1,5)));
    }
}
