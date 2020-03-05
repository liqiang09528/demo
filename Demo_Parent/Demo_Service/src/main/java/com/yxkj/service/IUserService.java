package com.yxkj.service;


import com.yxkj.pojo.User;
import com.yxkj.utils.ServerResponse;



public interface IUserService {

    /**
     * 登录接口
     *
     * @return*/
    ServerResponse login(String username, String password);

    /**
     * 注册接口
     *
     * @return*/
    ServerResponse register(User user);

     /**
      * isExistsByUsername 判断用户是否存在接口
      * */
     ServerResponse isExistsByUsername(String username);

     /**
      * isExistsEmail 判断邮箱是否存在
      * */
     ServerResponse isExistsEmail(String email);

    /**
     * isExistsEmail 判断邮箱是否存在
     * */
    ServerResponse isExistsPhone(String phone);
}
