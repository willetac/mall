package com.willetac.mmall01.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {
    private int status;
    private String msg;
    private T data;

    private Result(){}

    private Result(int status,String msg){
        this.status = status;
        this.msg = msg;
    }
    private Result(int status,T data){
        this.status = status;
        this.data = data;
    }
    private Result(int status,String msg,T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static <T> Result<T> success(String msg){
        return new Result<T>(ResponseCode.SUCCESS.getCode(),msg);
    }
    public static <T> Result<T> success(T data){
        return new Result<T>(ResponseCode.SUCCESS.getCode(),data);
    }
    public static <T> Result<T> error(String msg){
        return new Result<T>(ResponseCode.ERROR.getCode(),msg);
    }
    public static <T> Result<T> build(int status,T data){
        return new Result<T>(status,data);
    }
    public static <T> Result<T> build(int status,String msg,T data){
        return new Result<T>(status,msg,data);
    }
}
