package com.music.cloudmusicplayer.controller;

import com.music.cloudmusicplayer.service.UserService;
import com.music.cloudmusicplayer.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Peony
 * @Date: 2020/11/11 19:51
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/login")
    public Result<Integer> login(String userName,String userPassword) {
        Result<Integer> result = new Result<>();
        System.out.println(userName+" "+userPassword);
        return Result.generateSuccessfulResult(null);
    }
}
