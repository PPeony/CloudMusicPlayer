package com.music.cloudmusicplayer.controller;

import com.github.pagehelper.PageInfo;
import com.music.cloudmusicplayer.entity.Music;
import com.music.cloudmusicplayer.service.MusicService;
import com.music.cloudmusicplayer.util.CloudMusicUtil;
import com.music.cloudmusicplayer.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    public Result<PageInfo<Music>> getAllMusic(String token,HttpServletRequest request) {
        System.out.println("getAllMusic: "+token);
        Result<PageInfo<Music>> result = new Result<>();

        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @GetMapping("/search")
    public Result<Music> searchMusic(Music music,Integer pageNum,Integer pageSize) {
        System.out.println("searchMusic: "+music+"*"+pageNum+"*"+pageSize);
        Result<Music> result = new Result<>();

        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @PostMapping("/upload")
    public Result<String> uploadMusic(String token,MultipartFile music, HttpServletRequest request) {
        Result<String> result = new Result<>();
        System.out.println("token = "+token);
        String path = CloudMusicUtil.uploadFile(music, request);
        System.out.println(path);
        result.setCode(HttpStatus.CREATED.value());
        result.setMessage("success");
        result.setData(path);
        return result;
    }

    @PostMapping("/uploadByUrl")
    public Result<String> uploadMusicByUrl(String url,String token) {
        Result<String> result = new Result<>();

        result.setCode(HttpStatus.CREATED.value());
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
