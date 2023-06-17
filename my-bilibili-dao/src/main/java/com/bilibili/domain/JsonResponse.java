package com.bilibili.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author rlz
 * @date 2023/5/8 20:27
 * @description
 */
@Getter
@Setter
public class JsonResponse<T> {
    private String code;
    private String msg;
    private T data;

    public JsonResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResponse(T data) {
        this.data = data;
        msg = "success";
        code = "0";
    }

    public static JsonResponse<String> success() {
        return new JsonResponse<String>(null);
    }

    public static JsonResponse<String> success(String data) {
        return new JsonResponse<String>(data);
    }

    public static JsonResponse<String> fail() {
        return new JsonResponse<String>("1","fail");
    }
    public static JsonResponse<String> fail(String code,String msg) {
        return new JsonResponse<String>(code,msg);

    }
}
