package com.temmytech.temmy;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author TemmyTechie
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationEventPublisher publisher) throws Exception {
        //Dont look
        {
            http.getSharedObject(AuthenticationManagerBuilder.class)
                    .authenticationEventPublisher(publisher);
        }

        var authManager = new ProviderManager(new RobotAutenticatipnProvider(List.of("beep-bop", "boop-beep")));
        authManager.setAuthenticationEventPublisher(publisher);


        return http.authorizeRequests(authorizeConfig -> {
           authorizeConfig.requestMatchers("/").permitAll();
           authorizeConfig.requestMatchers("/error").permitAll();
           authorizeConfig.requestMatchers("/favicon.icon").permitAll();
           authorizeConfig.anyRequest().authenticated();
       })
               .formLogin(withDefaults())
               .oauth2Login(withDefaults())
               .addFilterBefore(new RobotFilter(authManager), UsernamePasswordAuthenticationFilter.class)
               .authenticationProvider(new MyAuthenticationManager())
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
                        .authorities("ROLE_user")
                        .build()
        );
    }

    @Bean
    public ApplicationListener<AuthenticationSuccessEvent> successListener()
    {
        return event -> {
            System.out.println(String.format(" success [%s] %s",event.getAuthentication().getClass()
                    .getName(), event.getAuthentication().getName())
            );
        };
    }
}
