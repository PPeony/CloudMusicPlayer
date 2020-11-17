package com.music.cloudmusicplayer.controller;

import com.music.cloudmusicplayer.entity.BackgroundPicture;
import com.music.cloudmusicplayer.service.BackgroundPictureService;
import com.music.cloudmusicplayer.util.CloudMusicUtil;
import com.music.cloudmusicplayer.util.Result;
import com.music.cloudmusicplayer.util.annotations.UserLoginToken;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Peony
 * @Date: 2020/11/12 10:13
 */
@RestController
@RequestMapping("/backgroundPicture")
public class BackgroundPictureController {
    @Resource
    BackgroundPictureService backgroundPictureService;


    @GetMapping("/{userId}")
    public Result<BackgroundPicture> getUserPicture(@PathVariable Integer userId) {
        Result<BackgroundPicture> result = new Result<>();
        BackgroundPicture picture = backgroundPictureService.getPictureByUserId(userId);
        result.setMessage("success");
        result.setCode(HttpStatus.OK.value());
        result.setData(picture);
        return result;
    }


    @PostMapping("/upload")
    public Result<Integer> uploadPicture(MultipartFile picture,BackgroundPicture backgroundPicture
    ,HttpServletRequest request) {
        Result<Integer> result = new Result<>();
        String path = CloudMusicUtil.uploadFile(picture,request);
        backgroundPicture.setBackgroundPicturePath(path);
        backgroundPictureService.uploadPicture(backgroundPicture);
        result.setMessage("success");
        result.setCode(HttpStatus.CREATED.value());
        return result;
    }

    @DeleteMapping("/delete/{backgroundPictureId}")
    public Result<Integer> deletePicture(@PathVariable Integer backgroundPictureId) {
        Result<Integer> result = new Result<>();
        backgroundPictureService.deletePicture(backgroundPictureId);
        result.setMessage("success");
        result.setCode(HttpStatus.OK.value());
        return result;
    }
}
