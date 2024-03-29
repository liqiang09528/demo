package com.yxkj.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> {
    private int status;//返回前端的状态码
    private T data;//返回前端的数据
    private String msg;//当status！=0时。封装了错误信息
    private ServerResponse(){}

    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse(int status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }
    /**
     * 调用接口成功时回调
     * */
    public static ServerResponse serverResponseBySuccess(){
        return new ServerResponse(ResponseCode.SUCESS);
    }
    public static <T> ServerResponse serverResponseBySuccess(T data){
        return new ServerResponse(ResponseCode.SUCESS,data);
    }public static <T> ServerResponse serverResponseBySuccess(T data,String msg){
        return new ServerResponse(ResponseCode.SUCESS,data,msg);
    }
    /**
     * 调用接口失败时回调
     * */
    public static ServerResponse serverResponseByError(){
        return new ServerResponse(ResponseCode.ERROR);
    }
    public static ServerResponse serverResponseByError(String msg){
        return new ServerResponse(ResponseCode.ERROR,msg);
    }
    public static ServerResponse serverResponseByError(int status){
        return new ServerResponse(status);
    }
    public static ServerResponse serverResponseByError(int status,String msg){
        return new ServerResponse(status,msg);
    }

    /**
     * 判断接口是否正确返回
     * status==0
     * */
    @JsonIgnore
    public boolean isSuccess(){
        return this.status==ResponseCode.SUCESS;
    }
}
