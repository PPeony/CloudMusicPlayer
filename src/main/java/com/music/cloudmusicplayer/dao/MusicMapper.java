package com.music.cloudmusicplayer.dao;

import com.music.cloudmusicplayer.entity.Music;

import java.util.List;

/**
 * @Author: Peony
 * @Date: 2020/11/12 14:50
 */
public interface MusicMapper {

    List<Music> selectAllMusicByUserId(Integer userId,String type);

    Music selectByMusicId(Integer musicId);

    List<Music> selectByProperties(Music music);

    Integer insert(Music music);

    Integer update(Music music);

}
