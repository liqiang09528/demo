package com.yxkj.service.impl;

import com.yxkj.dao.UserMapper;
import com.yxkj.pojo.User;
import com.yxkj.service.IUserService;
import com.yxkj.utils.MD5Utils;
import com.yxkj.utils.ResponseCode;
import com.yxkj.utils.RoleEnum;
import com.yxkj.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {


    @Autowired
    UserMapper userMapper;
    @Override
    public ServerResponse login(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        //1.判断用户是否存在
        int usernameResult=userMapper.isExistsByUsername(username);
        if (usernameResult<=0)
           return ServerResponse.serverResponseByError(ResponseCode.ERROR,"用户不存在");

        //2.密码加密
        password= MD5Utils.getMD5Code(password);
        //3.判断账户密码是否正确
        User user=userMapper.findUserByUsernameAndPassword(username,password);
        if (user==null)
           return ServerResponse.serverResponseByError(ResponseCode.ERROR,"密码或账户错误");

        return ServerResponse.serverResponseBySuccess(user);

    }

    @Override
    public ServerResponse register(User user) {
        //step1:参数校验
        if (user==null){
            return ServerResponse.serverResponseByError(ResponseCode.PARAM_NOT_NULL, "参数不能为空");
        }

        //step2：用户名是否存在

        int usernameResult = userMapper.isExistsByUsername(user.getUsername());

        if (usernameResult>0){
            return ServerResponse.serverResponseByError(ResponseCode.USERNAME_EXISTS, "用户名已存在");
        }
        //stem3：邮箱是否存在

        int emailResult = userMapper.isExistsEmail(user.getEmail());

        if (emailResult>0){
            return ServerResponse.serverResponseByError(ResponseCode.EMAIL_EXISTS, "邮箱已存在");
        }
        //step4：MD5密码加密，设置用户角色
        user.setPassword(MD5Utils.getMD5Code(user.getPassword()));
        user.setRole(RoleEnum.ROLE_USER.getRole());
        //step5：注册
        userMapper.insert(user);
        if (user.getId()==null){
            return ServerResponse.serverResponseByError(ResponseCode.ERROR, "注册失败");
        }
        User userResult=userMapper.selectByPrimaryKey(user.getId());
        //step6：返回
        return ServerResponse.serverResponseBySuccess(userResult);
    }

    @Override
    public ServerResponse isExistsByUsername(String username) {
        //step1：用户名是否存在
        int usernameResult = userMapper.isExistsByUsername(username);
        if (usernameResult>0){
            return ServerResponse.serverResponseByError(ResponseCode.USERNAME_EXISTS, "用户名已存在");
        }
        return ServerResponse.serverResponseBySuccess();
    }

    @Override
    public ServerResponse isExistsEmail(String email) {
        //stem1：邮箱是否存在
        int emailResult = userMapper.isExistsEmail(email);
        if (emailResult>0){
            return ServerResponse.serverResponseByError(ResponseCode.EMAIL_EXISTS, "邮箱已存在");
        }
        return ServerResponse.serverResponseBySuccess();
    }

    @Override
    public ServerResponse isExistsPhone(String phone) {
        //stem1：邮箱是否存在
        int emailResult = userMapper.isExistsPhone(phone);
        if (emailResult>0){
            return ServerResponse.serverResponseByError(ResponseCode.EMAIL_EXISTS, "电话已存在");
        }
        return ServerResponse.serverResponseBySuccess();
    }
}
