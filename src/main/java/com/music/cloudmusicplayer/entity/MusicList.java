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
public class MusicList {
    private Integer musicListId;
    private Integer userId;
    private String musicListName;
    private Date gmtCreated;
    private Integer isDeleted;
}
