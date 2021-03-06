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


    @UserLoginToken
    @GetMapping("/{userId}")
    public Result<BackgroundPicture> getUserPicture(@PathVariable Integer userId) {
        Result<BackgroundPicture> result = new Result<>();
        BackgroundPicture picture = backgroundPictureService.getPictureByUserId(userId);
        result.setMessage("success");
        result.setCode(HttpStatus.OK.value());
        result.setData(picture);
        return result;
    }


    @UserLoginToken
    @PostMapping("/upload")
    public Result<Integer> uploadPicture(MultipartFile picture
    ,HttpServletRequest request) {
        Result<Integer> result = new Result<>();
        BackgroundPicture backgroundPicture = new BackgroundPicture();
        String newName = CloudMusicUtil.uploadFile(picture);
        String path = CloudMusicUtil.getUrlPath(newName,request);
        backgroundPicture.setBackgroundPicturePath(path);
        backgroundPictureService.uploadPicture(backgroundPicture);
        result.setMessage("success");
        result.setCode(HttpStatus.CREATED.value());
        return result;
    }

    @UserLoginToken
    @DeleteMapping("/delete/{backgroundPictureId}")
    public Result<Integer> deletePicture(@PathVariable Integer backgroundPictureId) {
        Result<Integer> result = new Result<>();
        backgroundPictureService.deletePicture(backgroundPictureId);
        result.setMessage("success");
        result.setCode(HttpStatus.OK.value());
        return result;
    }
}
