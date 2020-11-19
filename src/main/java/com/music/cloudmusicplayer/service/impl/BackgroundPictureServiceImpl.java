package com.music.cloudmusicplayer.service.impl;

import com.music.cloudmusicplayer.dao.BackgroundPictureMapper;
import com.music.cloudmusicplayer.entity.BackgroundPicture;
import com.music.cloudmusicplayer.service.BackgroundPictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Peony
 * @Date: 2020/11/11 19:53
 */
@Service
public class BackgroundPictureServiceImpl implements BackgroundPictureService {

    @Resource
    BackgroundPictureMapper backgroundPictureMapper;

    @Override
    public BackgroundPicture getPictureByUserId(Integer userId) {
        List<BackgroundPicture> list = backgroundPictureMapper.selectByUserId(userId);
        return list.size() == 0 ? null : list.get(0);
    }

    @Override
    public BackgroundPicture getPictureByPictureId(Integer pictureId) {
        return backgroundPictureMapper.selectByPictureId(pictureId);
    }

    @Override
    public Integer uploadPicture(BackgroundPicture picture) {
        picture.setIsDeleted(0);
        backgroundPictureMapper.insert(picture);
        return 1;
    }

    @Override
    public Integer deletePicture(Integer backgroundPictureId) {
        BackgroundPicture backgroundPicture = new BackgroundPicture();
        backgroundPicture.setBackgroundPictureId(backgroundPictureId);
        backgroundPicture.setIsDeleted(1);
        backgroundPictureMapper.update(backgroundPicture);
        return 1;
    }

    @Override
    public Integer updateBackgroundPicture(BackgroundPicture picture) {
        backgroundPictureMapper.update(picture);
        return 1;
    }
}
