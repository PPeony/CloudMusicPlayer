package com.music.cloudmusicplayer.service.impl;

import com.music.cloudmusicplayer.dao.MusicListDetailMapper;
import com.music.cloudmusicplayer.dao.MusicListMapper;
import com.music.cloudmusicplayer.entity.Music;
import com.music.cloudmusicplayer.entity.MusicList;
import com.music.cloudmusicplayer.entity.MusicListDetail;
import com.music.cloudmusicplayer.service.MusicListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: Peony
 * @Date: 2020/11/11 19:53
 */
@Service
public class MusicListServiceImpl implements MusicListService {

    @Resource
    MusicListMapper musicListMapper;
    @Resource
    MusicListDetailMapper musicListDetailMapper;

    @Override
    public List<MusicList> getUserMusicList(Integer userId) {
        List<MusicList> list = musicListMapper.selectByUserId(userId);
        return list;
    }

    @Override
    public List<MusicListDetail> getMusicListDetails(Integer musicListId, String type) {
        List<MusicListDetail> list = musicListDetailMapper.selectByMusicListId(musicListId,type);
        return list;
    }

    @Override
    public Integer addMusicList(MusicList musicList) {
        MusicList origin = new MusicList();
        musicList.setIsDeleted(0);
        musicList.setMusicListName(musicList.getMusicListName());
        List<MusicList> origins = musicListMapper.selectBySelective(origin);
        if (origins.size() != 0) {
            return -1;
        }
        musicList.setGmtCreated(new Date());
        musicList.setIsDeleted(0);
        musicListMapper.insert(musicList);
        return 1;
    }

    @Override
    public Integer updateMusicList(MusicList musicList) {
        musicListMapper.update(musicList);
        return 1;
    }

    @Override
    public Integer addMusicToList(Integer musicId, Integer musicListId) {
        // list内不能有重复音乐
        MusicListDetail origin = new MusicListDetail();
        origin.setIsDeleted(0);
        origin.setMusic(new Music());
        origin.getMusic().setMusicId(musicId);
        origin.setMusicListId(musicListId);
        List<MusicListDetail> origins = musicListDetailMapper.selectBySelective(origin);
        if (origins.size() != 0) {
            // 歌曲已经在歌单中
            return -1;
        }
        // 包装成detail
        MusicListDetail detail = new MusicListDetail();
        detail.setGmtCreated(new Date());
        detail.setIsDeleted(0);
        detail.setMusicListId(musicListId);
        Music music = new Music();
        music.setMusicId(musicId);
        musicListDetailMapper.insert(detail);
        return 1;
    }

    @Override
    public Integer deleteMusicFromList(Integer musicListDetailId) {
        // 不会删除真实物理路径
        MusicListDetail detail = new MusicListDetail();
        detail.setMusicListDetailId(musicListDetailId);
        detail.setIsDeleted(1);
        musicListDetailMapper.update(detail);
        return null;
    }

    @Override
    public Integer deleteMusicList(Integer musicListId) {
        // 修改list表和detail表
        MusicList musicList = new MusicList();
        musicList.setIsDeleted(1);
        musicList.setMusicListId(musicListId);
        musicListMapper.update(musicList);
        MusicListDetail detail = new MusicListDetail();
        detail.setMusicListId(musicListId);
        detail.setMusic(new Music());
        detail.setIsDeleted(1);
        musicListDetailMapper.update(detail);
        return 1;
    }
}
