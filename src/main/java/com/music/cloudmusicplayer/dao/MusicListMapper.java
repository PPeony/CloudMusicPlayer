package com.music.cloudmusicplayer.dao;

import com.music.cloudmusicplayer.entity.Music;
import com.music.cloudmusicplayer.entity.MusicList;

import java.util.List;

/**
 * @Author: Peony
 * @Date: 2020/11/12 14:50
 */
public interface MusicListMapper {

    List<MusicList> selectBySelective(MusicList musicList);

    List<MusicList> selectByUserId(Integer userId);

    Integer insert(MusicList musicList);

    Integer update(MusicList musicList);


}
