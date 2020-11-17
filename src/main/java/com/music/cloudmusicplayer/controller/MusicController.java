package com.music.cloudmusicplayer.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.music.cloudmusicplayer.common.Property;
import com.music.cloudmusicplayer.entity.Music;
import com.music.cloudmusicplayer.service.MusicService;
import com.music.cloudmusicplayer.util.CloudMusicUtil;
import com.music.cloudmusicplayer.util.Result;
import com.music.cloudmusicplayer.util.annotations.UserLoginToken;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public Result<Music> getMusic(@PathVariable Integer musicId) {
        //System.out.println("getMusic: "+musicId+"*"+token);
        Result<Music> result = new Result<>();
        Music music = musicService.getMusic(musicId);
        if (music == null) {
            return Result.badRequestResult("no such id of music");
        }
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        result.setData(music);
        return result;
    }

    @UserLoginToken
    @GetMapping
    public Result<PageInfo<Music>> getAllMusic(@RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum,
                                               @RequestParam(required = false,defaultValue = "10",value = "pageSize")Integer pageSize,
                                               String type,
                                               HttpServletRequest request) {
        Integer userId = (Integer)request.getAttribute("userId");
        //System.out.println("userId = "+userId+"*");
        Result<PageInfo<Music>> result = new Result<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Music> list = musicService.getAllMusicByUserId(userId,type);
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        result.setData(new PageInfo<>(list));
        return result;
    }

    @GetMapping("/search")
    public Result<List<Music>> searchMusic(Music music,
                                     @RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum,
                                     @RequestParam(required = false,defaultValue = "10",value = "pageSize")Integer pageSize) {
        //System.out.println("searchMusic: "+music+"*"+pageNum+"*"+pageSize);

        Result<List<Music>> result = new Result<>();
        List<Music> list = musicService.searchMusic(music);
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        result.setData(list);
        return result;
    }

    @PostMapping("/upload")
    public Result<String> uploadMusic(MultipartFile musicFile,Music music, HttpServletRequest request) {
        Result<String> result = new Result<>();
        Integer userId = (Integer)request.getAttribute("userId");
        String path = CloudMusicUtil.uploadFile(musicFile, request);
        // todo,get music information through an util
        music.setMusicPath(path);
        music.setUserId(userId);
        musicService.uploadMusic(music);
        result.setCode(HttpStatus.CREATED.value());
        result.setMessage("success");
        result.setData(path);
        return result;
    }

    @PostMapping("/uploadByUrl")
    public Result<String> uploadMusicByUrl(Music music,HttpServletRequest request) {
        Result<String> result = new Result<>();
        Integer userId = (Integer)request.getAttribute("userId");
        music.setUserId(userId);
        musicService.uploadMusic(music);
        result.setCode(HttpStatus.CREATED.value());
        result.setMessage("success");
        return result;
    }

    @PutMapping("/update")
    public Result<Integer> updateMusic(Music music) {
        Result<Integer> result = new Result<>();
        musicService.updateMusic(music);
        result.setCode(HttpStatus.CREATED.value());
        result.setMessage("success");
        return result;
    }

    @DeleteMapping("/delete/{musicId}")
    public Result<Integer> deleteMusic(@PathVariable Integer musicId) {
        Result<Integer> result = new Result<>();
        musicService.deleteMusic(musicId);
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }
}
