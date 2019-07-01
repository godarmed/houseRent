package com.team.leo.controller.admin;

import com.github.pagehelper.PageInfo;
import com.team.leo.entity.Street;
import com.team.leo.service.street.StreetService;
import com.team.leo.util.StreetParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("adminStreetController")
@RequestMapping("/admin/street/")
public class StreetController {
    @Autowired
    private StreetService streetService;

    @RequestMapping(value = "selectStreet.do")
    public Map<String, Object> selectStreet(StreetParams params) {
        //查询数据
        PageInfo<Street> pageInfo = streetService.selectAllStreet(params);
        //添加分页数据
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping(value = "saveStreet.do")
    public Map<String,Object> Save(Street street) {
        int isSave = 0;
        isSave = streetService.insert(street);

        Map<String,Object> map = new HashMap<>();
        map.put("success",isSave!=0?true:false);
        return map;
    }

    @RequestMapping(value = "delStreet.do")
    public Map<String,Object> delStreet(@RequestParam(name = "ids") List<Integer> ids) {
        int deleteNum = 0;
        deleteNum = streetService.deleteByPrimaryKey(ids);

        Map<String,Object> map = new HashMap<>();
        map.put("success",deleteNum!=0?true:false);
        return map;
    }

    @RequestMapping(value = "modifyStreet.do")
    public Map<String, Object> updateStreet(Street street) {
        int updateNum = 0;
        updateNum = streetService.updateByPrimaryKey(street);

        Map<String,Object> map = new HashMap<>();
        map.put("success",updateNum!=0?true:false);
        return map;
    }
}
