package com.learn.spring.security.practice.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.learn.spring.security.practice.models.CustomUserDetail;
import com.learn.spring.security.practice.services.CustomUserDetailService;
import com.mysql.cj.protocol.AuthenticationProvider;


@Configuration
@EnableWebSecurity
public class MySecurityConfig{

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(customUserDetailService).passwordEncoder(this.passwordEncoder());

        http.csrf().disable().
        authorizeRequests()
        .requestMatchers("/signin").permitAll()
        .requestMatchers("/public/**").hasRole("NORMAL")
        .requestMatchers("/users/**").hasRole("ADMIN")
        .anyRequest()
        .authenticated()
        .and()
        // .httpBasic();
        .formLogin()
        .loginPage("/signin")
        .loginProcessingUrl("/dologin")
        .defaultSuccessUrl("/users/");
        return http.build();
    }

    // @Bean 
    // public UserDetailsService users(){
    //     UserDetails user = User.builder()
    //     .username("john")
    //     .password(this.passwordEncoder().encode("abc"))
    //     .roles("NORMAL","ADMIN")
    //     .build();
        
    //     UserDetails admin = User.builder()
    //     .username("roshni")
    //     .password(this.passwordEncoder().encode("abc"))
    //     .roles("ADMIN")
    //     .build();

    //     return new InMemoryUserDetailsManager(user,admin);

    // }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    
}
