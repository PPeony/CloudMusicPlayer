package com.music.cloudmusicplayer.dao;

import com.music.cloudmusicplayer.entity.BackgroundPicture;

import java.util.List;

/**
 * @Author: Peony
 * @Date: 2020/11/12 14:50
 */
public interface BackgroundPictureMapper {

    List<BackgroundPicture> selectByUserId(Integer userId);

    BackgroundPicture selectByPictureId(Integer pictureId);

    Integer insert(BackgroundPicture backgroundPicture);

    Integer update(BackgroundPicture backgroundPicture);
}
