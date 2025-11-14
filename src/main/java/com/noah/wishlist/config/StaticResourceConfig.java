package com.noah.wishlist.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {
    
    @Value("${wishlist.custom-css-dir}")
    private String customCssDir;
    @Value("${wishlist.custom-css-cache-period:3600}")
    private int customCssCachePeriod;
    
    @Value("${wishlist.custom-fav-icon}")
    private String customFavIcon;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (customCssDir == null || customCssDir.trim().isEmpty()) {
            registry.addResourceHandler("/custom/**")
                  .addResourceLocations(customCssDir)
                  .setCachePeriod(customCssCachePeriod);
        }
        
        if (customFavIcon != null && !customFavIcon.trim().isEmpty()) {
            registry.addResourceHandler("/favicon.ico")
                  .addResourceLocations(customFavIcon);
        }
    }

}
