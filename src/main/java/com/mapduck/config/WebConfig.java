package com.mapduck.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 작성자: 양태영
     * 작성일: 21.11.16
     * 설명: CORS설정
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 요청을 허용할 URL
                .allowedOrigins("*"); // 요청을 허용할 IP
    }
}
