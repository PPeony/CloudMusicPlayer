package com.music.cloudmusicplayer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: Peony
 * @Date: 2020/11/10 19:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicListDetail {
    private Integer musicListDetailId;
    private Integer musicListId;
    private Music music;
    private Date gmtCreated;
    private Integer isDeleted;
}
