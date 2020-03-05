package com.yxkj.controller;


import com.yxkj.pojo.User;
import com.yxkj.service.IUserService;
import com.yxkj.utils.ResponseCode;
import com.yxkj.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
@CrossOrigin
@RestController
@RequestMapping(value = "/user",method = RequestMethod.POST)
public class UserController {

    @Autowired
    IUserService userService;

    /**
     * 登录
     * */
    @RequestMapping("/login")
    public ServerResponse login(@RequestParam(value = "username",required = false) String username,
                                @RequestParam(value = "password",required = false) String password,
                                HttpSession session){
        //System.out.println(username);
        //System.out.println(password);
        if (username!=null&&username!=""&&password!=null &&password!="") {
            ServerResponse userResponse = userService.login(username, password);
            if (userResponse.isSuccess()){
                session.setAttribute("user",userResponse.getData());
            }
            return userResponse;
        }
        return ServerResponse.serverResponseByError(ResponseCode.ERROR,"请输入用户名和密码");
    }

    /**
     * 注册
     * */
    @RequestMapping(value = "/register")
    public ServerResponse register(@RequestBody User user,HttpSession session){
        //System.out.println(user);
        ServerResponse userResponse = userService.register(user);
        if (userResponse.isSuccess()){
            session.setAttribute("user",userResponse.getData());
        }
        return userResponse;
    }

    /**
     * 判断用户名是否存在
     * */
    @RequestMapping(value = "/isExistsByUsername")
    public ServerResponse isExistsByUsername(@RequestParam(value = "username",required = false) String username){
        System.out.println(username);
        return userService.isExistsByUsername(username);
    }

    /**
     * 判断邮箱是否存在
     * */
    @RequestMapping(value = "/isExistsEmail")
    public ServerResponse isExistsEmail(@RequestParam(value = "email",required = false) String email){
        System.out.println(email);
        return userService.isExistsEmail(email);
    }

    /**
     * 判断电话是否存在
     * */
    @RequestMapping(value = "/isExistsPhone")
    public ServerResponse isExistsPhone(@RequestParam(value = "phone",required = false) String phone){
        System.out.println(phone);
        return userService.isExistsPhone(phone);
    }

    /**
     * 退出登录
     * */
    @RequestMapping("/loginOut")
    public ServerResponse loginOut(HttpSession session){
        session.removeAttribute("user");
        return ServerResponse.serverResponseBySuccess();
    }
}
