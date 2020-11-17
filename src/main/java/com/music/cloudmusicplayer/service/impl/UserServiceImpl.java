package com.music.cloudmusicplayer.service.impl;

import com.music.cloudmusicplayer.dao.UserMapper;
import com.music.cloudmusicplayer.entity.User;
import com.music.cloudmusicplayer.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Peony
 * @Date: 2020/11/11 19:53
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User findUserById(Integer userId) {
        User user = new User();
        user.setUserId(userId);
        return userMapper.selectByUser(user);
    }

    @Override
    public User login(User user) {
        User selectedUser = userMapper.selectByUser(user);
        return selectedUser;
    }

    @Override
    public Integer insertUser(User user) {
        userMapper.insert(user);
        return 1;
    }

    @Override
    public Integer updateUser(User user) {
        userMapper.update(user);
        return 1;
    }

    @Override
    public Boolean findPassword(User user) {
        User origin = new User();
        // 根据名字，邮箱查询是否有一样的，若有一样说明可以修改
        origin.setUserName(user.getUserName());
        origin.setUserEmail(user.getUserEmail());
        User selected = userMapper.selectByUser(origin);
        if (selected == null) {
            return false;
        }
        User newUser = new User();
        // 设置修改用户的id
        newUser.setUserId(selected.getUserId());
        newUser.setUserPassword(user.getUserPassword());
        userMapper.update(newUser);
        return true;
    }

    @Override
    public Integer deleteUser(Integer userId) {
        User user = new User();
        user.setUserId(userId);
        user.setIsDeleted(1);
        userMapper.update(user);
        return null;
    }
}
