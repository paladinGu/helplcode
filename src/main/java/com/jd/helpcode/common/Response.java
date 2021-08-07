package com.jd.helpcode.common;

 
import lombok.Data;

import java.io.Serializable;


/**
 * @Descripe: 响应信息
 */

@Data
public class Response<T> implements Serializable {
    public static final String CODE_SUCCESS = "000000";
    public static final String MSG_SUCCESS = "SUCCESS";

    // 响应码
    private String code = "000000";

    // 信息
    private String message = "SUCCESS";

    // 数据
    private T data;


    public Response(T data) {
        this.data = data;
    }

    public Response(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public Response() {

    }

    public static <T> Response<T> result(T data, String code, String msg) {
        Response<T> response = new Response();
        response.setCode(code);
        response.setData(data);
        response.setMessage(msg);
        return response;
    }

    public static Response ofSuccess() {
        return new Response();
    }

    public static Response ofSuccess(String msg) {
        return new Response(msg);
    }

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response();
        response.setCode("000000");
        response.setData(data);
        response.setMessage("SUCCESS");
        return response;
    }

    public static Response success() {
        return success("");
    }

    public static <T> Response<T> ofSuccess(T data) {
        return new Response(data);
    }

 
 
}
