package com.music.cloudmusicplayer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: Peony
 * @Date: 2020/11/10 14:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private Date gmtCreated;
    private Date gmtModified;
    private Integer isDeleted;
}
