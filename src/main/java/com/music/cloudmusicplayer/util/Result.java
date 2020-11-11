package com.music.cloudmusicplayer.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

import java.io.Serializable;

/**
 * @Author: Peony
 * @Date: 2020/11/11 19:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {

    private String message;

    private Integer code;

    private T data;

    public static Result generateBadRequestResult(Errors errors) {
        Result result = new Result();
        result.setCode(HttpStatus.BAD_REQUEST.value());
        result.setMessage(errors.getAllErrors().get(0).getDefaultMessage());
        return result;
    }

    public static Result badUserParams(Integer r) {
        Result result = new Result();
        result.setCode(HttpStatus.BAD_REQUEST.value());
        switch (r) {
            case -1:result.setMessage("没有父级代理名字");break;
            case -2:result.setMessage("名字重复");break;
            case -3:result.setMessage("手机号重复");break;
            case -4:result.setMessage("邮箱重复");break;
        }
        return result;
    }

    public static Result generateSuccessfulResult(Object data) {
        Result result = new Result("success",HttpStatus.OK.value(),data);
        return result;
    }

    public Result code(int code) {
        this.code = code;
        return this;
    }

    public Result<T> data(T data) {
        this.data = data;
        return this;
    }

    public Result message(String message) {
        this.message = message;
        return this;
    }
}
