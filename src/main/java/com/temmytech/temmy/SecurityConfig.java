package com.temmytech.temmy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpRequest;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author TemmyTechie
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http.authorizeRequests(authorizeConfig -> {
           authorizeConfig.requestMatchers("/").permitAll();
           authorizeConfig.requestMatchers("/error").permitAll();
           authorizeConfig.requestMatchers("/favicon.icon").permitAll();
           authorizeConfig.anyRequest().authenticated();
       })
               .formLogin(withDefaults())
               .oauth2Login(withDefaults())
               .build();
    }

    @Bean
    public UserDetailsService userDetailsService()
    {

        //branch dev
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("user")
                        .password("{noop}password")
                        .authorities("ROLE_USER")
                        .build()
        );
    }
}
