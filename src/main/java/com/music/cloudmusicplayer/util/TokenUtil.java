package com.music.cloudmusicplayer.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.music.cloudmusicplayer.entity.User;

/**
 * @Author: Peony
 * @Date: 2020/11/16 19:42
 */
public class TokenUtil {
    public static String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(String.valueOf(user.getUserId()))
                .sign(Algorithm.HMAC256(user.getUserPassword()));
        return token;
    }
}
