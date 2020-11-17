package com.music.cloudmusicplayer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: Peony
 * @Date: 2020/11/10 14:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Music {

    private Integer musicId;

    private Integer userId;

    private String musicName;

    private String musicSinger;

    private Integer musicTime;

    private String musicLyrics;

    private String musicPath;

    private Date gmtCreated;

    private Integer isDeleted;
}
