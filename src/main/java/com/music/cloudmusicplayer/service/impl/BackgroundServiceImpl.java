package com.music.cloudmusicplayer.service.impl;

import com.music.cloudmusicplayer.entity.BackgroundPicture;
import com.music.cloudmusicplayer.service.BackgroundPictureService;
import org.springframework.stereotype.Service;

/**
 * @Author: Peony
 * @Date: 2020/11/11 19:53
 */
@Service
public class BackgroundServiceImpl implements BackgroundPictureService {
    @Override
    public BackgroundPicture getPictureByPictureId(Integer pictureId) {
        return null;
    }

    @Override
    public Integer uploadPicture(BackgroundPicture picture) {
        return null;
    }

    @Override
    public Integer deletePicture(Integer backgroundPictureId) {
        return null;
    }

    @Override
    public Integer updateBackgroundPicture(BackgroundPicture picture) {
        return null;
    }
}
