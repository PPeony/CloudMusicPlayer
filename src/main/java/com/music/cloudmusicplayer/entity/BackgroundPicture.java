package com.music.cloudmusicplayer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Peony
 * @Date: 2020/11/10 14:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackgroundPicture {
    private Integer backgroundPictureId;
    private Integer userId;
    private String backgroundPicturePath;
    private Integer isDeleted;
}
