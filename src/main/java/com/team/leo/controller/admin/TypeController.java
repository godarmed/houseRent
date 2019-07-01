package com.team.leo.controller.admin;

import com.github.pagehelper.PageInfo;
import com.team.leo.entity.Type;
import com.team.leo.service.type.TypeService;
import com.team.leo.util.TypeParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("adminTypeController")
@RequestMapping("/admin/type/")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @RequestMapping(value = "selectType.do")
    public Map<String, Object> selectType(TypeParams params) {
        //查询数据
        PageInfo<Type> pageInfo = typeService.selectAllType(params);
        //添加分页数据
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping(value = "saveType.do")
    public Map<String,Object> Save(Type type) {
        int isSave = 0;
        isSave = typeService.insert(type);

        Map<String,Object> map = new HashMap<>();
        map.put("success",isSave!=0?true:false);
        return map;
    }

    @RequestMapping(value = "delType.do")
    public Map<String,Object> delType(@RequestParam(name = "ids") List<Integer> ids) {
        int deleteNum = 0;
        deleteNum = typeService.deleteByPrimaryKey(ids);

        Map<String,Object> map = new HashMap<>();
        map.put("success",deleteNum!=0?true:false);
        return map;
    }

    @RequestMapping(value = "modifyType.do")
    public Map<String, Object> updateType(Type type) {
        int updateNum = 0;
        updateNum = typeService.updateByPrimaryKey(type);
        Map<String,Object> map = new HashMap<>();
        map.put("success",updateNum!=0?true:false);
        return map;
    }
}

