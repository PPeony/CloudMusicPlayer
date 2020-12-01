package com.music.cloudmusicplayer.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.music.cloudmusicplayer.common.Property;
import com.music.cloudmusicplayer.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Peony
 * @Date: 2020/11/16 19:42
 */
public class TokenUtil {


    // 私钥是密码的md5
    public static String generateToken(User user) {
        Map<String,Object> claims = new HashMap<>(1);
        claims.put("userId",user.getUserId());
        return generateToken(claims);
    }

    public static String generateToken(Map<String,Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateTokenDate())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, Property.SECRET)
                .compact();
    }

    public static Date generateTokenDate() {
        return new Date(System.currentTimeMillis()+3600*1000);//3600秒，1小时
    }

    public static boolean checkTokenRefreshed(String token,Date lastPasswordReset) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(Property.SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            final Date iat = claims.getIssuedAt();
            final Date exp = claims.getExpiration();
            if (iat.before(lastPasswordReset) || exp.before(new Date(System.currentTimeMillis()))) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(Property.SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public static Integer verifyToken(String token) {
        Claims claims;
        Integer userId;
        try {
            claims = Jwts.parser()
                    .setSigningKey(Property.SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            userId = (Integer)claims.get("userId");
            return userId;
        } catch (Exception e) {
            return null;
        }
    }
}
