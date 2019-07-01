package com.team.leo.controller.admin;

import com.github.pagehelper.PageInfo;
import com.team.leo.entity.Users;
import com.team.leo.service.users.UsersService;
import com.team.leo.util.UsersParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("adminUsersController")
@RequestMapping("/admin/users/")
public class UsersController {
    @Autowired
    private UsersService usersService;

    /**
     *  公用方法
     *
     */
    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping(value = "userLogin.do")
    public Map<String, Object> userlogin(Users user,HttpServletRequest request, HttpSession session, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String,Object> map = new HashMap<>();
        //判断用户是否存在且用户名、密码是否正确
        Users isUserRight = usersService.checkUser(user);
        map.put("success",isUserRight==null?false:true);
        if(isUserRight == null){
            return map;
        }

        //更新session
        session.setAttribute("userInfo",isUserRight);
        session.setMaxInactiveInterval(3600); //设置session有效时间为3600秒

        //更新用户信息cookie(中文需要手动编码为utf-8)
        Cookie cookie = new Cookie("userInfo", URLEncoder.encode(user.getName(),"utf-8"));
        //设置cookie路径
        cookie.setPath(request.getContextPath() + "/");
        //设置cookie有效期
        cookie.setMaxAge(3600);
        //添加cookie
        response.addCookie(cookie);

        //修改返回头以跨域返回cookie
        response.setHeader("Access-Control-Allow-Credentials","true");
        return map;
    }

    /**
     * 判断用户是否已经登录,如果登录就返回用户的用户名
     * @return
     */
    @RequestMapping(value="isLogin.do")
    public Map<String, Object> isLogin(HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException {
        //创建要返回的json(Map形式)
        Map<String,Object> map = new HashMap<>();
        //读取cookie并校验
        Cookie[] cookies = request.getCookies();
        if(cookies != null){    //判断cookie是否为空
            for (Cookie c : cookies) {
                if(c.getName().equalsIgnoreCase("userInfo")){ //判断userInfo是否存在
                    if(session.getAttribute("userInfo") != null){ //服务端校验cookie
                        map.put("islogin",true);
                        return map;
                    }
                }
                //System.out.println(URLDecoder.decode(c.getValue(),"utf-8"));
            }
            System.out.println(session.getAttribute("userInfo"));
        }
        map.put("islogin",false);
        return map;
    }

    /**
     * 判断用户是否已经登录,如果登录就返回用户的用户名
     * @return
     */
    @RequestMapping(value="getUserName.do")
    public Map<String, Object> getUserName(HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException {
        //创建要返回的json(Map形式)
        Map<String,Object> map = new HashMap<>();
        //读取cookie并校验
        Cookie[] cookies = request.getCookies();
        if(cookies != null){    //判断cookie是否为空
            for (Cookie c : cookies) {
                if(c.getName().equalsIgnoreCase("userInfo")){ //判断userInfo是否存在
                    if(session.getAttribute("userInfo") != null){ //服务端校验cookie
                        map.put("userName",URLDecoder.decode(c.getValue(),"utf-8"));
                        return map;
                    }
                }
                /*
                System.out.println(URLDecoder.decode(c.getValue(),"utf-8"));
                System.out.println(session.getAttribute(c.getValue()));
                */
            }
        }
        return map;
    }

    /*
    *   前台方法
    *
    * */

    /**
     * 判断用户是否存在
     * @param name
     * @return
     */
    @RequestMapping(value = "isUserExist.do")
    public Map<String, Object> isUserExist(String name) {
        //判断用户是否存在
        Boolean isUserExist = usersService.isUserExist(name);
        Map<String,Object> map = new HashMap<>();
        map.put("success",isUserExist);
        return map;
    }


    /*
    *  后台方法
    *
    * */
    @RequestMapping(value = "selectAllUsersWithoutParams")
    public Map<String, Object> selectUserAll(UsersParams params) {
        //查询数据
        PageInfo<Users> pageInfo = usersService.selectAllUsers(params.getPage(),params.getRows());
        //添加分页数据
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping(value = "selectUsers")
    public Map<String, Object> selectUsers(UsersParams params) {
        //查询数据
        PageInfo<Users> pageInfo = usersService.selectAllUsers(params);
        //添加分页数据
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping(value = "saveUser.do")
    public Map<String,Object> Save(Users user) {
        Map<String,Object> map = new HashMap<>();
        if(usersService.isUserExist(user.getName())){
            map.put("success",false);
        }else{
            int isSave = 0;
            isSave = usersService.insert(user);
            map.put("success",isSave!=0?true:false);
        }
        return map;
    }

    @RequestMapping(value = "delUsers.do")
    public Map<String,Object> delUsers(@RequestParam(name = "ids") List<Integer> ids) {
        int deleteNum = 0;
        deleteNum = usersService.deleteByPrimaryKey(ids);

        Map<String,Object> map = new HashMap<>();
        map.put("success",deleteNum!=0?true:false);
        return map;
    }

    @RequestMapping(value = "modifyUser.do")
    public String updateUser(Users users) {
        int updateNum = 0;
        updateNum = usersService.updateByPrimaryKey(users);
        return "{success:" + (updateNum!=0?true:false) + "}";
    }
}
