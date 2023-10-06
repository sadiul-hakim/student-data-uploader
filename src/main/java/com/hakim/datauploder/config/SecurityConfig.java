package com.hakim.datauploder.config;

import com.hakim.datauploder.security.CustomAuthenticationFilter;
import com.hakim.datauploder.security.CustomAuthorizationFilter;
import com.hakim.datauploder.service.CustomUserDetailsService;
import com.hakim.datauploder.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAuthorizationFilter customAuthorizationFilter;

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception{

        String[] privateApis = {
                "/user/**",
                "/role/**"
        };
        String[] publicApis = {
                "/data-importer/**",
                "/presence/**",
                "/student/**",
                "/fee/**",
                "/fee-data/**",
                "/subject/**",
                "/result-data/**",
                "/data-type/**",
                "/section/**",
                "/department/**"
        };

        String[] permitAll = {
                "/refreshToken"
        };

        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers(permitAll).permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers(privateApis).hasRole(Role.ADMIN.name()))
                .authorizeHttpRequests(auth -> auth.requestMatchers(publicApis).hasRole(Role.TEACHER.name()))
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(customAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilter(new CustomAuthenticationFilter(authenticationProvider()))
                .build();
    }

    public DaoAuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(customUserDetailsService);

        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
