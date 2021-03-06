package com.music.cloudmusicplayer.dao;

import com.music.cloudmusicplayer.entity.User;

import java.util.List;

/**
 * @Author: Peony
 * @Date: 2020/11/12 14:51
 */
public interface UserMapper {
    List<User> selectByUser(User user);

    Integer insert(User user);

    Integer update(User user);

}
