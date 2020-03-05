package com.yxkj.config;


import com.yxkj.utils.ResponseCode;
import com.yxkj.utils.ServerResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 拦截器配置
 * */
public class LoginInterceptor implements HandlerInterceptor
{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        // 在拦截点执行前拦截，如果返回true则不执行拦截点后的操作（拦截成功）
        // 返回false则不执行拦截
        HttpSession session = request.getSession();
        String url = request.getRequestURI(); // 获取登录的uri，这个是不进行拦截的
        //if(session.getAttribute("_CURRENT_USER")!=null || url.indexOf("home.action")!=-1 || url.indexOf("login.action")!=-1) {
        if(session.getAttribute("user")!=null) {
            // 登录成功不拦截
            return true;
        }else {
            // 拦截后返回信息
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(ServerResponse.serverResponseByError(ResponseCode.NOT_LOGIN,"请登录"));
            return false;
        }
    }
}
