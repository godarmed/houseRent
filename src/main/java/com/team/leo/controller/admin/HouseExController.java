package com.team.leo.controller.admin;

import com.github.pagehelper.PageInfo;
import com.team.leo.entity.House;
import com.team.leo.entity.HouseEx;
import com.team.leo.entity.Users;
import com.team.leo.service.house.HouseService;
import com.team.leo.service.houseEx.HouseExService;
import com.team.leo.util.HouseParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController("adminHouseExController")
@RequestMapping("/admin/houseEx/")
public class HouseExController {
    @Autowired
    private HouseExService houseExService;

    @Autowired
    private HouseService houseService;


    /*前台方法*/
    @RequestMapping(value = "selectHouseExByUserId.do")
    public Map<String, Object> selectHouseExByUserId(HouseParams params, HttpSession session) {
        //设置userId
        params.setUserId(((Users)session.getAttribute("userInfo")).getId());
        //查询数据
        PageInfo<HouseEx> pageInfo = houseExService.selectByExample(params);
        //添加分页数据
        Map<String,Object> map = new HashMap<>();
        map.put("pageInfo",pageInfo);
        return map;
    }

    @RequestMapping(value = "selectHouseExByHouseId.do")
    public Map<String, Object> selectHouseExByHouseId(String houseId, HttpSession session) {
        //查询数据
        HouseEx houseEx = houseExService.selectByPrimaryKey(houseId);
        //添加分页数据
        Map<String,Object> map = new HashMap<>();
        map.put("houseInfo",houseEx);
        return map;
    }

    /**
     * 管理员查看出租房信息
     * @param params
     * @return
     */
    @RequestMapping(value = "selectHouseByAdmin.do")
    public Map<String, Object> selectHouse(HouseParams params) {
        //查询数据
        PageInfo<HouseEx> pageInfo = houseExService.selectByExample(params);
        //添加分页数据
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    /**
     * 管理员修改出租房审核状态
     * @param house
     * @return
     */
    @RequestMapping(value = "setHousePass.do")
    public Map<String, Object> setHousePass(House house) {
        System.out.println(house);
        //修改房屋审核状态
        house.setIspass(house.getIspass()==0?1:0);
        int updateNum = 0;
        //查询数据
        updateNum = houseService.updateByPrimaryKeySelective(house);
        //添加分页数据
        Map<String,Object> map = new HashMap<>();
        map.put("success",updateNum==0?false:true);
        return map;
    }
}
