package com.team.leo.controller.user;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.team.leo.entity.HouseEx;
import com.team.leo.entity.Users;
import com.team.leo.service.houseEx.HouseExService;
import com.team.leo.util.HouseParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("userHouseExController")
@RequestMapping("/user/houseEx/")
public class HouseExController {
    @Autowired
    private HouseExService houseExService;

    /*前台方法*/

    /**
     * 用户查询用户的房屋
     * @param params
     * @param session
     * @return
     */
    @RequestMapping(value = "selectHouseExByUserId.do")
    @ResponseBody
    public Map<String, Object> selectHouseExByUserId(HouseParams params, HttpSession session) {
        //设置userId
        params.setUserId(((Users)session.getAttribute("userInfo")).getId());
        return selectHouse(params);
    }

    /**
     * 用户查询所有的房屋
     * @param params
     * @return
     */
    @RequestMapping(value = "selectHouseEx.do")
    public void selectHouseExBy(HouseParams params, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //我这里是返回 json 类型
        response.setContentType("application/json;charset=utf-8");

        //通过response对象获得输出流。
        PrintWriter out = response.getWriter();

        //构建对象转json类
        ObjectMapper mapper = new ObjectMapper();
        /**
         * 这里是我返回给前端的对象,其他类型也可以
         */
        //把 对象转为 json 字符串
        String json = mapper.writeValueAsString(selectHouse(params));

        //把数据写到response对象上。
        // 注： Servlet容器会将response对象上存放的数据取出来
        //，打一个包（响应数据包）， 然后发送给浏览器。
        out.println(json);

        //如果没有调用out.close，则容器会自动 关闭out。
        out.close();
    }

    @RequestMapping(value = "selectHouseExByHouseId.do")
    @ResponseBody
    public Map<String, Object> selectHouseExByHouseId(String houseId) {
        //查询数据
        HouseEx houseEx = houseExService.selectByPrimaryKey(houseId);
        //添加分页数据
        Map<String,Object> map = new HashMap<>();
        map.put("houseInfo",houseEx);
        return map;
    }

    /*后台方法*/

    /**
     * 管理员查看出租房信息
     * @param params
     * @return
     */
    @RequestMapping(value = "selectHouseByAdmin.do")
    @ResponseBody
    public Map<String, Object> selectHouse(HouseParams params) {
        //查询数据
        PageInfo<HouseEx> pageInfo = houseExService.selectByExample(params);
        //添加分页数据
        Map<String,Object> map = new HashMap<>();
        map.put("pageInfo",pageInfo);
        return map;
    }
}
