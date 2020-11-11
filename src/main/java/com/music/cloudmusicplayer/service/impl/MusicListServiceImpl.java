package com.music.cloudmusicplayer.service.impl;

import com.music.cloudmusicplayer.entity.Music;
import com.music.cloudmusicplayer.entity.MusicList;
import com.music.cloudmusicplayer.service.MusicListService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Peony
 * @Date: 2020/11/11 19:53
 */
@Service
public class MusicListServiceImpl implements MusicListService {
    @Override
    public List<MusicList> getUserMusicList(Integer userId) {
        return null;
    }

    @Override
    public List<Music> getMusicListDetails(Integer musicListId, String type) {
        return null;
    }

    @Override
    public Integer addMusicList(MusicList musicList) {
        return null;
    }

    @Override
    public Integer updateMusicList(MusicList musicList) {
        return null;
    }

    @Override
    public Integer addMusicToList(Integer musicId, Integer musicListId) {
        return null;
    }

    @Override
    public Integer deleteMusicFromList(Integer musicId, Integer musicListId) {
        return null;
    }

    @Override
    public Integer deleteMusicList(Integer musicListId) {
        return null;
    }
}
