package com.music.cloudmusicplayer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "请输入歌单名字")
    private String musicListName;

    private Date gmtCreated;

    private Integer isDeleted;
}
