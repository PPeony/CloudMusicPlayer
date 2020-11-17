package com.music.cloudmusicplayer.controller;

import com.music.cloudmusicplayer.entity.User;
import com.music.cloudmusicplayer.service.UserService;
import com.music.cloudmusicplayer.util.CloudMusicUtil;
import com.music.cloudmusicplayer.util.Result;
import com.music.cloudmusicplayer.util.TokenUtil;
import com.music.cloudmusicplayer.util.annotations.PassToken;
import com.music.cloudmusicplayer.util.annotations.UserLoginToken;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author: Peony
 * @Date: 2020/11/11 19:51
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @PassToken
    @PostMapping("/login")
    public Result<User> login(@RequestBody User user, HttpServletResponse response) {
        Result<Integer> result = new Result<>();

        if (CloudMusicUtil.checkString(user.getUserName()) ||
        CloudMusicUtil.checkString(user.getUserPassword())) {
            return Result.badRequestResult("请输入用户名或密码");
        }


        // success:
        User selectedUser = userService.login(user);
        if (selectedUser == null) {
            return Result.badRequestResult("用户名或者密码错误");
        }
        String token = TokenUtil.getToken(selectedUser);
        response.setHeader("token",token);
        return Result.generateSuccessfulResult(selectedUser);
    }

    // 测试token
    @UserLoginToken
    @GetMapping("/testToken")
    public Result<Integer> testToken() {
        Result<Integer> result = new Result<>();
        System.out.println("success");
        return Result.generateSuccessfulResult(null);
    }

    @PassToken
    @PostMapping("/register")
    public Result<Integer> register(@Valid @RequestBody User user, Errors errors) {
        //System.out.println("register: "+user);
        Result<Integer> result = new Result<>();
        if (errors.hasErrors()) {
            return Result.badRequestResult(errors.getAllErrors().get(0).getDefaultMessage());
        }
        Integer r = userService.insertUser(user);
        if (r != 1) {
            result.setMessage("用户名或者邮箱已存在");
            result.setCode(HttpStatus.BAD_REQUEST.value());
            return result;
        }
        result.setCode(HttpStatus.CREATED.value());
        result.setMessage("success");
        return result;
    }

    @PassToken
    @PostMapping("/findPassword")
    public Result<Integer> findPassword(@RequestBody User user) {
        //System.out.println("findPassword: "+user);
        Result<Integer> result = new Result<>();
        if (CloudMusicUtil.checkString(user.getUserName()) ||
                CloudMusicUtil.checkString(user.getUserEmail())) {
            return Result.badRequestResult("请输入用户名或密码");
        }
        boolean r = userService.findPassword(user);
        if (!r) {
            result.setCode(HttpStatus.BAD_REQUEST.value());
            result.setMessage("用户名或者邮箱输入错误");
            return result;
        }
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @UserLoginToken
    @PutMapping("/modify")
    public Result<Integer> modifyPersonalMsg(@RequestBody User user) {
//        System.out.println("modify: "+user);
        Result<Integer> result = new Result<>();
        Integer r = userService.updateUser(user);
        if (r != 1) {
            result.setMessage("用户名或者邮箱已存在");
            result.setCode(HttpStatus.BAD_REQUEST.value());
            return result;
        }
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }
}
