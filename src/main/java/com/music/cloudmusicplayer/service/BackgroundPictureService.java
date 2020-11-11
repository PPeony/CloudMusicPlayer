package com.music.cloudmusicplayer.service;

import com.music.cloudmusicplayer.entity.BackgroundPicture;

/**
 * @Author: Peony
 * @Date: 2020/11/10 14:23
 */
public interface BackgroundPictureService {
    /**
     * @param pictureId
     * @return com.music.cloudmusicplayer.entity.BackgroundPicture
     */
    BackgroundPicture getPictureByPictureId(Integer pictureId);

    /** 上传主页图片
     * @param picture
     * @return java.lang.Integer
     */
    Integer uploadPicture(BackgroundPicture picture);

    /**
     * @param backgroundPictureId
     * @return java.lang.Integer
     */
    Integer deletePicture(Integer backgroundPictureId);

    /**
     * @param picture
     * @return java.lang.Integer
     */
    // 用不上
    Integer updateBackgroundPicture(BackgroundPicture picture);
}
