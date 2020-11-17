package com.music.cloudmusicplayer.service;

import com.music.cloudmusicplayer.entity.Music;
import com.music.cloudmusicplayer.entity.MusicList;
import com.music.cloudmusicplayer.entity.MusicListDetail;

import java.util.List;

/**
 * @Author: Peony
 * @Date: 2020/11/10 14:23
 */
public interface MusicListService {

    /**
     * 通过user_id得到用户所有的歌单，按照歌单名字排序
     * @param userId
     * @return java.util.List<com.music.cloudmusicplayer.entity.MusicList>
     */
    List<MusicList> getUserMusicList(Integer userId);

    /** 通过musicListId得到所有的music
     * @param musicListId
	 * @param type 排序方式（音乐名称，歌手名称），默认为主键id
     * @return java.util.List<com.music.cloudmusicplayer.entity.Music>
     */
    List<MusicListDetail> getMusicListDetails(Integer musicListId,String type);

    /**
     * 创建新歌单
     * @param musicList
     * @return java.lang.Integer
     */
    Integer addMusicList(MusicList musicList);

    /**
     * 修改歌单
     * @param musicList
     * @return java.lang.Integer
     */
    // till now, only name can be changed
    Integer updateMusicList(MusicList musicList);


    /**
     * 添加音乐到某个歌单
     * @param musicId
	 * @param musicListId
     * @return java.lang.Integer
     */
    Integer addMusicToList(Integer musicId,Integer musicListId);

    /** 删除detail
     * @param musicListDetailId
     * @return java.lang.Integer
     */
    Integer deleteMusicFromList(Integer musicListDetailId);

    /**
     * 删除整个歌单
     * @param musicListId
     * @return java.lang.Integer
     */
    Integer deleteMusicList(Integer musicListId);

}
