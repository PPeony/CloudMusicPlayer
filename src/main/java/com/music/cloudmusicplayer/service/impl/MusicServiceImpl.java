package com.music.cloudmusicplayer.service.impl;

import com.github.pagehelper.PageInfo;
import com.music.cloudmusicplayer.dao.MusicListDetailMapper;
import com.music.cloudmusicplayer.dao.MusicListMapper;
import com.music.cloudmusicplayer.dao.MusicMapper;
import com.music.cloudmusicplayer.entity.Music;
import com.music.cloudmusicplayer.entity.MusicListDetail;
import com.music.cloudmusicplayer.service.MusicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: Peony
 * @Date: 2020/11/11 19:54
 */
@Service
public class MusicServiceImpl implements MusicService {

    @Resource
    MusicMapper musicMapper;

    @Resource
    MusicListDetailMapper musicListDetailMapper;

    @Override
    public List<Music> getAllMusicByUserId(Integer userId, String type) {

        List<Music> list = musicMapper.selectAllMusicByUserId(userId, type);
        return list;
    }

    @Override
    public Music getMusic(Integer musicId) {
        Music music = musicMapper.selectByMusicId(musicId);
        return music;
    }

    @Override
    public List<Music> searchMusic(Music music) {
        List<Music> list = musicMapper.selectByProperties(music);
        return list;
    }

    @Override
    public Integer uploadMusic(Music music) {
        // todo, controller层应该调用一个util，处理出歌名-歌手-时长,
        //  也就是说，这一层得到的是封装好的music，待测试
        music.setGmtCreated(new Date());
        music.setIsDeleted(0);
        musicMapper.insert(music);
        return 1;
    }

    @Override
    public Integer updateMusic(Music music) {
        musicMapper.update(music);
        return 1;
    }

    @Override
    public Integer deleteMusic(Integer musicId) {
        // todo,真实的删除物理路径
        // 同时删除music表和musicListDetail表
        Music music = new Music();
        music.setIsDeleted(1);
        music.setMusicId(musicId);
        musicMapper.update(music);
        MusicListDetail detail = new MusicListDetail();
        detail.setIsDeleted(1);
        detail.setMusic(music);
        musicListDetailMapper.update(detail);
        return 1;
    }
}
