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
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
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

    //@UserLoginToken
    @GetMapping
    public Result<PageInfo<Music>> getAllMusic(@RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam(required = false,defaultValue = "10",value = "pageSize")Integer pageSize,@RequestParam(required = false,defaultValue = "music_id",value = "type")String type,HttpServletRequest request) {
        Integer userId = (Integer)request.getAttribute("userId");
        userId = 1;
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
    public Result<List<Music>> searchMusic(Music music) {
        Result<List<Music>> result = new Result<>();
        List<Music> list = musicService.searchMusic(music);
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        result.setData(list);
        return result;
    }

    @PostMapping("/upload")
    public Result<String> uploadMusic(MultipartFile musicFile,
                                      @RequestParam(required = false,defaultValue = "1",value = "type") Integer type,
                                      HttpServletRequest request) {
        Music music = new Music();
        Result<String> result = new Result<>();
        // todo ,token replace
        Integer userId = (Integer)request.getAttribute("userId");
        userId = 1;
        String name = musicFile.getOriginalFilename();
        System.out.println("name = "+name);
        // 计算歌曲时长

        String newName = CloudMusicUtil.uploadFile(musicFile);
        int time = getMp3TrackLength(new File(Property.FILE_PATH+newName));
        System.out.println("new path is => "+(Property.FILE_PATH+newName));
        String path = CloudMusicUtil.getUrlPath(newName,request);
        // 拆出singer和music_name
        String[] params = name.split("\\.");
        String[] params2 = params[0].split("-");
        // type-1:musicName-singer,type-2:singer-musicName,default is 1
        if (type == 1) {
            music.setMusicName(params2[0]);
            music.setMusicSinger(params2[1]);
        } else if (type ==2){
            music.setMusicName(params2[1]);
            music.setMusicSinger(params2[0]);
        } else {
            return Result.badRequestResult("type 只能为1和2");
        }
        music.setMusicPath(path);


        music.setMusicTime(time);
        music.setUserId(userId);
        musicService.uploadMusic(music);
        result.setCode(HttpStatus.CREATED.value());
        result.setMessage("success");
        result.setData(path);
        return result;
    }

    private int getMp3TrackLength(File mp3File) {
        try {
            MP3File f = (MP3File) AudioFileIO.read(mp3File);
            MP3AudioHeader audioHeader = (MP3AudioHeader)f.getAudioHeader();
            return audioHeader.getTrackLength();
        } catch(Exception e) {
            return -1;
        }
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
        // todo,同时删除musicList的
        result.setCode(HttpStatus.OK.value());
        result.setMessage("success");
        return result;
    }
}
