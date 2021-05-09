package com.app.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebServiceConfig implements WebMvcConfigurer{

    @Autowired
    LoginInterCeptor loginInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        
        System.out.println("WebServiceConfig:addInterceptors:Inter Ceptors");
        WebMvcConfigurer.super.addInterceptors(registry);
        
        String[] excludePatterns = {
                                     "/auth/**",
                                     "/swagger-ui.html*"
                                   };
        
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns(excludePatterns)
                .addPathPatterns("/api/**");
                
    }
}
