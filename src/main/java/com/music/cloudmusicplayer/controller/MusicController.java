package com.music.cloudmusicplayer.controller;

import com.github.pagehelper.PageInfo;
import com.music.cloudmusicplayer.entity.Music;
import com.music.cloudmusicplayer.service.MusicService;
import com.music.cloudmusicplayer.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Author: Peony
 * @Date: 2020/11/11 20:25
 */
@RestController
@RequestMapping("/music")
public class MusicController {

    @Resource
    MusicService musicService;

    @GetMapping("/{musicId}")
    public Result<Music> getMusic(@PathVariable Integer musicId,String token) {
        System.out.println("getMusic: "+musicId+"*"+token);
        Result<Music> result = new Result<>();

        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @GetMapping
    public Result<Music> getAllMusic(String token) {
        System.out.println("getAllMusic: "+token);
        Result<Music> result = new Result<>();

        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @GetMapping("/search")
    public Result<PageInfo<Music>> searchMusic(Music music,Integer pageNum,Integer pageSize) {
        System.out.println("searchMusic: "+music+"*"+pageNum+"*"+pageSize);
        Result<PageInfo<Music>> result = new Result<>();

        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @PostMapping("/upload")
    public Result<Integer> uploadMusic(MultipartFile music,String token) {
        Result<Integer> result = new Result<>();

        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @PutMapping("/update")
    public Result<Integer> updateMusic(Music music) {
        Result<Integer> result = new Result<>();

        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @DeleteMapping("/delete/{musicId}")
    public Result<Integer> deleteMusic(@PathVariable Integer musicId) {
        Result<Integer> result = new Result<>();

        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }
}
