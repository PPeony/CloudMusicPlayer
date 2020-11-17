package com.music.cloudmusicplayer.config;

import com.music.cloudmusicplayer.common.Property;
import com.music.cloudmusicplayer.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Peony
 * @Date: 2020/11/12 10:46
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        /**
         * 配置资源映射
         * 意思是：如果访问的资源路径是以“/images/”开头的，
         * 就给我映射到本机的“D:/images/”这个文件夹内，去找你要的资源
         * 注意：D:/images/ 后面的 “/”一定要带上
         */
        registry.addResourceHandler("/musicfile/**")
                .addResourceLocations("file:"+ Property.FILE_PATH);
    }

    /**
     * 添加拦截器
     *
     */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**");
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
}
