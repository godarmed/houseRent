package com.team.leo.controller.user;
import com.github.pagehelper.PageInfo;
import com.team.leo.entity.House;
import com.team.leo.entity.Users;
import com.team.leo.service.house.HouseService;
import com.team.leo.util.HouseParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("UserHouseController")
@RequestMapping("/user/house/")
public class HouseController {
    private static final String imgUrl = "C:\\Java\\nginx-1.17.1\\html\\";

    @Autowired
    private HouseService houseService;

    /**
     * 用户按用户编号搜索房屋
     * @param params
     * @param session
     * @return
     */
    @RequestMapping(value = "selectHouseByUserId.do")
    public Map<String, Object> selectHouseByUserId(HouseParams params,HttpSession session) {
        //设置userId
        params.setUserId(((Users)session.getAttribute("userInfo")).getId());
        //查询数据
        PageInfo<House> pageInfo = houseService.selectAllHouse(params);
        //添加分页数据
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    /**
     * 用户按房屋编号搜索房屋
     * @param houseId
     * @return
     */
    @RequestMapping(value = "selectHouseByHouseId.do")
    public Map<String, Object> selectHouseByHouseId(String houseId) {
        //查询数据
        House house = houseService.selectByPrimaryKey(houseId);
        //添加分页数据
        Map<String,Object> map = new HashMap<>();
        map.put("houseInfo",house);
        return map;
    }

    /**
     * 按条件搜索房屋
     * @param params
     * @return
     */
    @RequestMapping(value = "selectHouse.do")
    public Map<String, Object> selectHouse(HouseParams params) {
        //查询数据
        PageInfo<House> pageInfo = houseService.selectAllHouse(params);
        //添加分页数据
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    /**
     * 储存房屋信息
     * @param house
     * @param pfile
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "saveHouse.do")
    public Map<String,Object> Save(House house, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile, HttpSession session) throws IOException {
        //判断是否有文件上传
        if(pfile != null) {
            //将文件保存在服务器中
            String fname = pfile.getOriginalFilename();
            String expName = fname.substring(fname.lastIndexOf("."));
            String saveName = System.currentTimeMillis() + expName; //保存文件名
            File file = new File(imgUrl + saveName);
            pfile.transferTo(file);  //保存
            //设置图片路径
            house.setPath(saveName);
        }else{
            //设置图片为默认路径
            house.setPath(imgUrl + "default.png");
        }

        //设置用户id
        if(house.getUserId() == null){
            System.out.println(((Users)session.getAttribute("userInfo")).getId());
            house.setUserId(((Users)session.getAttribute("userInfo")).getId());
        }
        //设置房屋id
        if(house.getId() == null || house.getId().trim().equals("")){
            house.setId(System.currentTimeMillis()+"");
        }
        //设置是否审核通过
        if(house.getIspass() == null){
            house.setIspass(0);
        }
        //设置是否删除
        if(house.getIsdel() == null){
            house.setIsdel(0);
        }
        
        int isSave = 0;
        isSave = houseService.insert(house);

        Map<String,Object> map = new HashMap<>();
        map.put("success",isSave!=0?true:false);
        return map;
    }

    /**
     * 逻辑删除房屋信息
     * @param ids
     * @return
     */
    @RequestMapping(value = "delHouse.do")
    public Map<String,Object> delHouse(@RequestParam(name = "ids") List<String> ids) {
        //创建house对象
        House house = new House();
        //设置isdel值
        house.setIsdel(1);
        //循环修改isdel值
        int deleteNum = 0;
        for (String id : ids) {
            //设置主键值
            house.setId(id);
            deleteNum += houseService.updateByPrimaryKeySelective(house);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("success",deleteNum!=0?true:false);
        return map;
    }

    /**
     * 修改房屋信息
     * @param house
     * @param pfile
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "modifyHouse.do")
    public Map<String, Object> updateHouse(House house, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile) throws IOException {
        //将房屋状态置于未审核状态
        house.setIspass(0);

        //判断是否有文件上传
        if(pfile != null  &&  pfile.getSize() != 0){
            //查询原有图片路径并删除该图片
            File oldPic = new File(imgUrl + houseService.selectByPrimaryKey(house.getId()).getPath());
            oldPic.delete();

            //将文件保存在服务器中
            String fname=pfile.getOriginalFilename();
            String expName=fname.substring(fname.lastIndexOf("."));
            String saveName=System.currentTimeMillis()+expName; //保存文件名
            File file=new File(imgUrl + saveName);
            pfile.transferTo(file);  //保存

            //设置图片路径到数据库中
            house.setPath(saveName);
        }

        //更新数据库
        int updateNum = 0;
        updateNum = houseService.updateByPrimaryKeySelective(house);
        Map<String,Object> map = new HashMap<>();
        map.put("success",updateNum!=0?true:false);
        return map;
    }
}

