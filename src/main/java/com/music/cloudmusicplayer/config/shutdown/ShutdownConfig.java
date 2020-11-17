package com.music.cloudmusicplayer.config.shutdown;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Peony
 * @Date: 2020/11/12 14:40
 */
@Configuration
public class ShutdownConfig {

    @Bean
    public CustomShutdown customShutdown() {
        return new CustomShutdown();
    }

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory(final CustomShutdown customShutdown) {
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        tomcatServletWebServerFactory.addConnectorCustomizers(customShutdown);
        return tomcatServletWebServerFactory;
    }
}
