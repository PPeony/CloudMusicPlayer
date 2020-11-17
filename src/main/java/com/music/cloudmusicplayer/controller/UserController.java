package com.music.cloudmusicplayer.controller;

import com.music.cloudmusicplayer.entity.User;
import com.music.cloudmusicplayer.service.UserService;
import com.music.cloudmusicplayer.util.Result;
import com.music.cloudmusicplayer.util.TokenUtil;
import com.music.cloudmusicplayer.util.annotations.UserLoginToken;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
    public Result<Integer> login(@RequestBody User user, HttpServletResponse response) {
        Result<Integer> result = new Result<>();
        // success:
        User selectedUser = userService.login(user);
        selectedUser = new User();
        selectedUser.setUserId(1);
        selectedUser.setUserName("1");
        selectedUser.setUserPassword("12345678");
        String token = TokenUtil.getToken(selectedUser);
        response.setHeader("token",token);
        return Result.generateSuccessfulResult(null);
    }

    @UserLoginToken
    @GetMapping("/testToken")
    public Result<Integer> testToken() {
        Result<Integer> result = new Result<>();
        System.out.println("success");
        return Result.generateSuccessfulResult(null);
    }

    @PostMapping("/register")
    public Result<Integer> register(@RequestBody User user) {
        System.out.println("register: "+user);
        Result<Integer> result = new Result<>();

        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @PostMapping("/findPassword")
    public Result<Integer> findPassword(@RequestBody User user) {
        System.out.println("findPassword: "+user);
        Result<Integer> result = new Result<>();

        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @PutMapping("/modify")
    public Result<Integer> modifyPersonalMsg(@RequestBody User user) {
        System.out.println("modify: "+user);
        Result<Integer> result = new Result<>();

        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }
}
