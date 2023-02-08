package com.junwork.chatbot.global.config;

import com.junwork.chatbot.global.config.interceptor.LogInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final String[] DEFAULT_EXCLUDE_PATHS = { "/css/**", "/js/**", "/img/**", "/lib/**", "/*.ico",};
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(DEFAULT_EXCLUDE_PATHS);

    }
}
