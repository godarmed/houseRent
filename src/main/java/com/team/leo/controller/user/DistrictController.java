package com.team.leo.controller.user;

import com.github.pagehelper.PageInfo;
import com.team.leo.entity.District;
import com.team.leo.service.district.DistrictService;
import com.team.leo.util.DistrictParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("UserDistrictController")
@RequestMapping("/user/district/")
public class DistrictController {
    @Autowired
    private DistrictService districtService;
    
    @RequestMapping(value = "selectDistrict.do")
    public Map<String, Object> selectDistrict(DistrictParams params) {
        //查询数据
        PageInfo<District> pageInfo = districtService.selectDistrict(params);
        //添加分页数据
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;

    }

    @RequestMapping(value = "saveDistrict.do")
    public Map<String,Object> Save(District user) {
        int isSave = 0;
        isSave = districtService.insert(user);

        Map<String,Object> map = new HashMap<>();
        map.put("success",isSave!=0?true:false);
        return map;
    }

    @RequestMapping(value = "delDistrict.do")
    public Map<String,Object> delDistrict(@RequestParam(name = "ids") List<Integer> ids) {
        int deleteNum = 0;
        deleteNum = districtService.deleteByPrimaryKey(ids);

        Map<String,Object> map = new HashMap<>();
        map.put("success",deleteNum!=0?true:false);
        return map;
    }

    @RequestMapping(value = "modifyDistrict.do")
    public Map<String,Object> updateDistrict(District district) {
        int updateNum = 0;
        updateNum = districtService.updateByPrimaryKey(district);
        Map<String,Object> map = new HashMap<>();
        map.put("success",updateNum!=0?true:false);
        return map;
    }
}
