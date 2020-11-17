package com.music.cloudmusicplayer.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 * @Author: Peony
 * @Date: 2020/11/17 16:08
 */
@Data
@NoArgsConstructor
public class TokenException extends RuntimeException{
    private String msg;
    public TokenException(String msg) {
        super(msg);
    }
}
