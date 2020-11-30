package com.music.cloudmusicplayer.controller;

import com.music.cloudmusicplayer.entity.Music;
import com.music.cloudmusicplayer.entity.MusicList;
import com.music.cloudmusicplayer.entity.MusicListDetail;
import com.music.cloudmusicplayer.service.MusicListService;
import com.music.cloudmusicplayer.service.MusicService;
import com.music.cloudmusicplayer.util.Result;
import com.music.cloudmusicplayer.util.annotations.UserLoginToken;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.BindingType;
import java.util.List;
import java.util.Map;

/**
 * @Author: Peony
 * @Date: 2020/11/12 9:56
 */
@RestController
@RequestMapping("/list")
public class MusicListController {

    @Resource
    MusicListService musicListService;

    @UserLoginToken
    @GetMapping("/{userId}")
    public Result<List<MusicList>> getUserList(@PathVariable Integer userId) {
        Result<List<MusicList>> result = new Result<>();
        List<MusicList> list = musicListService.getUserMusicList(userId);
        result.setData(list);
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @UserLoginToken
    @GetMapping("/getDetails")
    public Result<List<MusicListDetail>> getMusicListDetails(@RequestBody Integer musicListId,
                                                             @RequestBody @RequestParam(required = false,defaultValue =
                                                                     "music_id",
                                                                     value = "type")String type) {
        Result<List<MusicListDetail>> result = new Result<>();
        System.out.println("getMusicListDetails: "+musicListId+"*"+type);
        List<MusicListDetail> list = musicListService.getMusicListDetails(musicListId,type);
        //System.out.println(list);
        result.setData(list);
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @UserLoginToken
    @PostMapping("/add")
    public Result<Integer> addMusicList(@RequestBody MusicList musicList, HttpServletRequest request) {
        Result<Integer> result = new Result<>();
        Integer userId = Integer.valueOf((String)request.getAttribute("userId"));
        musicList.setUserId(userId);
        Integer r = musicListService.addMusicList(musicList);
        if (r != 1) {
            return Result.badRequestResult("歌单名字已存在");
        }
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @UserLoginToken
    @PutMapping("/update")
    public Result<Integer> updateMusicList(@RequestBody MusicList musicList) {
        System.out.println(musicList);
        Result<Integer> result = new Result<>();
        musicListService.updateMusicList(musicList);
        result.setCode(HttpStatus.CREATED.value());
        result.setMessage("success");
        return result;
    }

    @UserLoginToken
    @PostMapping("/addMusic")
    public Result<Integer> addMusicToMusicList(@RequestBody Map<String,Integer> json) {
        Integer musicId = json.get("musicId");
        Integer musicListId = json.get("musicListId");
        System.out.println("musicId = "+musicId+" listId = "+musicListId);
        Result<Integer> result = new Result<>();
        Integer r = musicListService.addMusicToList(musicId,musicListId);
        if (r != 1) {
            return Result.badRequestResult("该歌曲已存在于该歌单");
        }
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @UserLoginToken
    @DeleteMapping("/deleteMusic/{musicListDetailId}")
    public Result<Integer> deleteMusicFromMusicList(@PathVariable Integer musicListDetailId) {
        System.out.println(musicListDetailId);
        Result<Integer> result = new Result<>();
        musicListService.deleteMusicFromList(musicListDetailId);
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }

    @UserLoginToken
    @DeleteMapping("/delete/{musicListId}")
    public Result<Integer> deleteMusicList(@PathVariable Integer musicListId) {
        Result<Integer> result = new Result<>();
        musicListService.deleteMusicList(musicListId);
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }
}
