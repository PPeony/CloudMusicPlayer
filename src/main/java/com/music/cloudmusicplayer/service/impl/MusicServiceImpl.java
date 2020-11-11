package com.music.cloudmusicplayer.service.impl;

import com.github.pagehelper.PageInfo;
import com.music.cloudmusicplayer.entity.Music;
import com.music.cloudmusicplayer.service.MusicService;
import org.springframework.stereotype.Service;

/**
 * @Author: Peony
 * @Date: 2020/11/11 19:54
 */
@Service
public class MusicServiceImpl implements MusicService {
    @Override
    public PageInfo<Music> getAllMusicByUserId(Integer userId, String type) {
        return null;
    }

    @Override
    public Music getMusic(Integer musicId) {
        return null;
    }

    @Override
    public PageInfo<Music> searchMusic(Music music) {
        return null;
    }

    @Override
    public Integer uploadMusic(Music music) {
        return null;
    }

    @Override
    public Integer updateMusic(Music music) {
        return null;
    }

    @Override
    public Integer deleteMusic(Integer musicId) {
        return null;
    }
}
