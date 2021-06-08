package com.music.cloudmusicplayer.controller;

import com.music.cloudmusicplayer.entity.User;
import com.music.cloudmusicplayer.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Peony
 * @Date: 2020/12/16 15:16
 */
@RestController
@RequestMapping("/test")
public class Test {
    @GetMapping("/get")
    public Result testGet(String name) {
        System.out.println(name);
        Result result = new Result();
        result.setCode(HttpStatus.OK.value());
        return result;
    }

    @PostMapping("/post")
    public Result testPost(@RequestBody User user) {
        System.out.println(user);
        Result result = new Result();
        result.setData(user);
        result.setCode(HttpStatus.CREATED.value());
        return result;
    }
}
