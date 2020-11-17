package com.music.cloudmusicplayer.controller;

import com.music.cloudmusicplayer.entity.Music;
import com.music.cloudmusicplayer.entity.MusicList;
import com.music.cloudmusicplayer.service.MusicListService;
import com.music.cloudmusicplayer.service.MusicService;
import com.music.cloudmusicplayer.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.ws.BindingType;
import java.util.List;

/**
 * @Author: Peony
 * @Date: 2020/11/12 9:56
 */
@RestController
@RequestMapping("/list")
public class MusicListController {
    @Resource
    MusicListService musicListService;

    @Resource
    MusicService musicService;

    @GetMapping("/{userId}")
    public Result<List<MusicList>> getUserList(@PathVariable Integer userId) {
        Result<List<MusicList>> result = new Result<>();
        System.out.println("getUserList: "+userId);
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @GetMapping("/getDetails")
    public Result<List<MusicList>> getMusicListDetails(Integer musicListId,String type) {
        Result<List<MusicList>> result = new Result<>();
        System.out.println("getMusicListDetails: "+musicListId+"*"+type);
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @PostMapping("/add")
    public Result<Integer> addMusicList(MusicList musicList) {
        Result<Integer> result = new Result<>();

        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @PutMapping("/update")
    public Result<Integer> updateMusicList(MusicList musicList) {
        Result<Integer> result = new Result<>();

        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @PostMapping("/addMusic")
    public Result<Integer> addMusicToMusicList(Integer musicId,Integer musicListId) {
        Result<Integer> result = new Result<>();

        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @DeleteMapping("/deleteMusic")
    public Result<Integer> deleteMusicFromMusicList(Integer musicId,Integer musicListId) {
        Result<Integer> result = new Result<>();

        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @DeleteMapping("/delete/{musicListId}")
    public Result<Integer> deleteMusicList(@PathVariable Integer musicListId) {
        Result<Integer> result = new Result<>();

        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }
}
