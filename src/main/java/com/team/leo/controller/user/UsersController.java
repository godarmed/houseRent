package com.team.leo.controller.user;
import com.team.leo.entity.Users;
import com.team.leo.service.users.UsersService;
import com.team.leo.util.getVerCode.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

@RestController("userUsersController")
@RequestMapping("/user/users/")
public class UsersController {
    @Autowired
    private UsersService usersService;

    /**
     * 获得验证码
     * @param userPhone
     * @param request
     * @param session
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "getVercode.do")
    public Map<String, Object> getVercode(String userPhone,HttpServletRequest request, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        //获取用户手机号
        if(userPhone == null){
            map.put("success",false);
            return map;
        }

        //创建四位短信验证码并存入session中
        int vercode = (int)(Math.random()*10000);
        System.out.println(vercode);
        session.setAttribute("userVercode", vercode);
        session.setMaxInactiveInterval(120); //设置session有效时间为120秒

        //**************
        //发送短信验证码
        int success = 0;
        StringBuilder sb = new StringBuilder("您好,号码为");
        sb.append(userPhone);
        sb.append("的用户.您的验证登录密码为:");
        sb.append(vercode);
        System.out.println(sb);
        success = SmsUtil.sendMsm(sb.toString(),userPhone);
        //**************

        if(success > 0){
            map.put("success",true);
        }else{
            map.put("success",false);
        }

        return map;
    }


    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping(value = "userLogin.do")
    public Map<String, Object> userlogin(Users user,String userVercode,HttpServletRequest request, HttpSession session, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String,Object> map = new HashMap<>();

        //判断验证码是否正确
        if(!userVercode.equals("") && userVercode.equals(((Integer)(session.getAttribute("userVercode"))).toString())){
            map.put("success",true);
        }else{
            map.put("success",false);
            return map;
        }

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

    /**
     * 添加用户
     * @param user
     * @return
     */
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
}
