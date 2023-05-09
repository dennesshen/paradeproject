package com.parade.paradeproject.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author christinehsieh on 2023/5/3
 */
@Configuration
@EnableWebSecurity
public class ParadeWebSecurityConfig {

    @Autowired
    private ParadeUserService paradeUserService;

    @Autowired
    private ParadeAuthenticationEntryPoint paradeAuthenticationEntryPoint;

    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

         http.csrf().disable()
             .authorizeRequests()
             .antMatchers("/auth/login").permitAll()
             .antMatchers("/swagger-ui/**",
                          "/swagger-resources/**",
                          "/v3/api-docs/**").permitAll()
             .anyRequest().authenticated()
             .and()
             .authenticationProvider(authenticationProvider())
             .exceptionHandling().authenticationEntryPoint(paradeAuthenticationEntryPoint)
             .and()
             .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    private AuthenticationProvider authenticationProvider() {

        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(paradeUserService);
        provider.setPasswordEncoder(bCryptPasswordEncoder());

        return provider;
    }


    @Bean
    public AuthenticationManager authenticationManagerBean(){
        return new ProviderManager(authenticationProvider());
    }




}
