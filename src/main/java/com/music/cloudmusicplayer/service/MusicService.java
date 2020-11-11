package com.music.cloudmusicplayer.service;

import com.github.pagehelper.PageInfo;
import com.music.cloudmusicplayer.entity.Music;

/**
 * @Author: Peony
 * @Date: 2020/11/10 14:23
 */
public interface MusicService {

    /** 根据用户id得到所有的music
     * @param userId
	 * @param type ,排序方式(音乐名称和歌手名称两种)，默认为主键id
     * @return com.github.pagehelper.PageInfo<com.music.cloudmusicplayer.entity.Music>
     */
    PageInfo<Music> getAllMusicByUserId(Integer userId,String type);

    /** 通过id得到music
     * @param musicId
     * @return com.music.cloudmusicplayer.entity.Music
     */
    Music getMusic(Integer musicId);

    /** 通过各种条件查询
     * @param music
     * @return com.github.pagehelper.PageInfo<com.music.cloudmusicplayer.entity.Music>
     */
    PageInfo<Music> searchMusic(Music music);

    /** 上传music
     * @param music
     * @return java.lang.Integer
     */
    Integer uploadMusic(Music music);

    /** 修改某个music
     * @param music
     * @return java.lang.Integer
     */
    // only permit to update name and singer
    Integer updateMusic(Music music);

    /** 删除music
     * @param musicId
     * @return java.lang.Integer
     */
    // change database status but delete real music
    Integer deleteMusic(Integer musicId);
}
