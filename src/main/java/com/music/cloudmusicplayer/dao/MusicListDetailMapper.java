package com.music.cloudmusicplayer.dao;

import com.music.cloudmusicplayer.entity.Music;
import com.music.cloudmusicplayer.entity.MusicListDetail;

import java.util.List;

/**
 * @Author: Peony
 * @Date: 2020/11/16 16:23
 */
public interface MusicListDetailMapper {

    List<MusicListDetail> selectByMusicListId(Integer musicListId,String type);

    Integer insert(MusicListDetail detail);

    Integer update(MusicListDetail detail);
}
