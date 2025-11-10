package com.noah.wishlist.security;

import java.security.Security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurity {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
              .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/edit").authenticated()
                    .anyRequest().permitAll())
              .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
              .build();
    }
}