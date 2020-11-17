package com.music.cloudmusicplayer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.music.cloudmusicplayer.dao")
public class CloudMusicPlayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudMusicPlayerApplication.class, args);
    }

    // todo,除了登录以外都应该对未登录用户拦截
}
