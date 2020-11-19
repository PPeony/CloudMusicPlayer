package com.music.cloudmusicplayer.controller;

import com.music.cloudmusicplayer.entity.Music;
import com.music.cloudmusicplayer.entity.MusicList;
import com.music.cloudmusicplayer.entity.MusicListDetail;
import com.music.cloudmusicplayer.service.MusicListService;
import com.music.cloudmusicplayer.service.MusicService;
import com.music.cloudmusicplayer.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/{userId}")
    public Result<List<MusicList>> getUserList(@PathVariable Integer userId) {
        Result<List<MusicList>> result = new Result<>();
        List<MusicList> list = musicListService.getUserMusicList(userId);
        result.setData(list);
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @GetMapping("/getDetails")
    public Result<List<MusicListDetail>> getMusicListDetails(Integer musicListId,
                                                             @RequestParam(required = false,defaultValue = "music_id",
                                                                     value = "type")String type) {
        Result<List<MusicListDetail>> result = new Result<>();
        System.out.println("getMusicListDetails: "+musicListId+"*"+type);
        List<MusicListDetail> list = musicListService.getMusicListDetails(musicListId,type);
        result.setData(list);
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @PostMapping("/add")
    public Result<Integer> addMusicList(MusicList musicList, HttpServletRequest request) {
        Result<Integer> result = new Result<>();
        Integer userId = (Integer)request.getAttribute("userId");
        userId = 1;
        musicList.setUserId(userId);
        musicListService.addMusicList(musicList);
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @PutMapping("/update")
    public Result<Integer> updateMusicList(MusicList musicList) {
        Result<Integer> result = new Result<>();
        musicListService.updateMusicList(musicList);
        result.setCode(HttpStatus.CREATED.value());
        result.setMessage("success");
        return result;
    }

    @PostMapping("/addMusic")
    public Result<Integer> addMusicToMusicList(Integer musicId,Integer musicListId) {
        Result<Integer> result = new Result<>();
        musicListService.addMusicToList(musicId,musicListId);
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @DeleteMapping("/deleteMusic")
    public Result<Integer> deleteMusicFromMusicList(Integer musicListDetailId) {
        Result<Integer> result = new Result<>();
        musicListService.deleteMusicFromList(musicListDetailId);
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @DeleteMapping("/delete/{musicListId}")
    public Result<Integer> deleteMusicList(@PathVariable Integer musicListId) {
        Result<Integer> result = new Result<>();
        musicListService.deleteMusicList(musicListId);
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }
}
