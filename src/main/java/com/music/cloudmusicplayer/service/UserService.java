package com.music.cloudmusicplayer.service;

import com.music.cloudmusicplayer.entity.User;

/**
 * @Author: Peony
 * @Date: 2020/11/10 14:22
 */
public interface UserService {
    /**
     * @param userId
     * @return com.music.cloudmusicplayer.entity.User
     */
    User findUserById(Integer userId);

    /**
     * @param user
     * @return java.lang.Boolean
     */
    User login(User user);

    /**
     * @param user
     * @return java.lang.Integer
     */
    Integer insertUser(User user);

    /**
     * @param user
     * @return java.lang.Integer
     */
    Integer updateUser(User user);

    /** 找回密码，输入正确的用户名和email就可以找回
     * @param user
     * @return java.lang.Boolean
     */
    Boolean findPassword(User user);

    /**
     * @param userId
     * @return java.lang.Integer
     */
    // 不对外开放
    Integer deleteUser(Integer userId);
}
