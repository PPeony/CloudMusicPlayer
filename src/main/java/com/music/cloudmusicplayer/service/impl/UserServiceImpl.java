package com.music.cloudmusicplayer.service.impl;

import com.music.cloudmusicplayer.dao.UserMapper;
import com.music.cloudmusicplayer.entity.User;
import com.music.cloudmusicplayer.service.UserService;
import com.music.cloudmusicplayer.util.CloudMusicUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
        List<User> list = userMapper.selectByUser(user);
        return list.size() == 0 ? null : list.get(0);
    }

    @Override
    public User login(User user) {
        user.setUserPassword(CloudMusicUtil.MD5(user.getUserPassword()));
        List<User> selectedUser = userMapper.selectByUser(user);

        return selectedUser.size() == 0 ? null : selectedUser.get(0);
    }

    @Override
    public Integer insertUser(User user) {
        // 用户名,邮箱唯一
        User user1 = new User();
        user1.setUserName(user.getUserName());
        List<User> list = userMapper.selectByUser(user1);
        if (list.size() != 0) {
            return 0;
        }
        user1.setUserName(null);
        user1.setUserEmail(user.getUserEmail());
        list = userMapper.selectByUser(user1);
        if (list.size() != 0) {
            return 0;
        }
        user.setUserPassword(CloudMusicUtil.MD5(user.getUserPassword()));
        user.setGmtCreated(new Date());
        user.setGmtModified(new Date());
        user.setIsDeleted(0);
        userMapper.insert(user);
        return 1;
    }

    @Override
    public Integer updateUser(User user) {
//        修改名字和email注意不能重复
        if (user.getUserName() != null) {
            User u = new User();
            u.setUserName(user.getUserName());
            List<User> list = userMapper.selectByUser(u);
            if (list.size() != 0) {
                return 0;
            }
        }
        if (user.getUserEmail() != null) {
            User u = new User();
            u.setUserName(user.getUserEmail());
            List<User> list = userMapper.selectByUser(u);
            if (list.size() != 0) {
                return 0;
            }
        }
        user.setGmtModified(new Date());
        userMapper.update(user);
        return 1;
    }

    @Override
    public Boolean findPassword(User user) {
        User origin = new User();
        // 根据名字，邮箱查询是否有一样的，若有一样说明可以修改
        origin.setUserName(user.getUserName());
        origin.setUserEmail(user.getUserEmail());
        List<User> selected = userMapper.selectByUser(origin);
        if (selected.size() == 0) {
            return false;
        }
        User newUser = new User();
        // 设置修改用户的id
        newUser.setUserId(selected.get(0).getUserId());
        newUser.setUserPassword(CloudMusicUtil.MD5(user.getUserPassword()));
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
