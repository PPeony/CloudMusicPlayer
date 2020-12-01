package com.music.cloudmusicplayer.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.music.cloudmusicplayer.config.TokenException;
import com.music.cloudmusicplayer.entity.User;
import com.music.cloudmusicplayer.service.UserService;
import com.music.cloudmusicplayer.util.TokenUtil;
import com.music.cloudmusicplayer.util.annotations.PassToken;
import com.music.cloudmusicplayer.util.annotations.UserLoginToken;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Peony
 * @Date: 2020/11/16 19:45
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token

        System.out.println(" find token is:"+token+"====");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new TokenException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                Integer result = TokenUtil.verifyToken(token);
                System.out.println("token 解析得到userId是 "+result);
                if (result == null) {
                    throw new TokenException("token解析错误");
                }
                httpServletRequest.setAttribute("userId",result);
                String refreshedToken = TokenUtil.refreshToken(token);
                httpServletResponse.setHeader("access_token", refreshedToken);
                httpServletResponse.addHeader("Access-Control-Expose-Headers","token");
                //======
                /*
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                    System.out.println("get userId = "+userId);
                    // 设置userId字段
                    httpServletRequest.setAttribute("userId",userId);
                } catch (JWTDecodeException j) {

                    throw new TokenException("token解析错误");
                }
                User user = userService.findUserById(Integer.valueOf(userId));

                if (user == null) {

                    throw new TokenException("用户不存在，请重新登录");
                }


                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUserPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {

                    throw new TokenException("token解析错误");
                }
                return true;

                 */
            }


        }
        return true;
    }




}
