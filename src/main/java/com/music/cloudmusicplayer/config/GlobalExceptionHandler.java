package com.music.cloudmusicplayer.config;

import com.music.cloudmusicplayer.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Peony
 * @Date: 2020/11/12 11:11
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e, HttpServletRequest req) {
        //打印错误
        log.error("error: ",e);
        e.printStackTrace();
        Result result = new Result();
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            //404
            result.setCode(HttpStatus.NOT_FOUND.value());
            result.setMessage("找不到页面");
        } else if (e instanceof org.springframework.web.HttpRequestMethodNotSupportedException) {
            //405
            result.setCode(HttpStatus.METHOD_NOT_ALLOWED.value());
            result.setMessage("请求类型不正确");
        } else {
            //500
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage("服务器异常");
        }

        return result;

    }
}
