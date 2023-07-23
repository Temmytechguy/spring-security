package com.temmytech.temmy;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import java.util.Collections;


/**
 * @author TemmyTechie
 */

public class RobotFilter extends OncePerRequestFilter {


    private static final String HEADER_NAME = "x-robot-password";

    private final AuthenticationManager authenticationManager;

    public RobotFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(!Collections.list(request.getHeaderNames()).contains("x-robot-passowrd"))
        {
            filterChain.doFilter(request, response);
            return;
        }
        //1. Authentication Decision

        var  password = request.getHeader(HEADER_NAME);
        var authRequest = RobotAuthentication.unAuthenticated(password);

        try{
            var authentication = authenticationManager.authenticate(authRequest);
            var newContext     = SecurityContextHolder.createEmptyContext();
            newContext.setAuthentication((authentication));
            SecurityContextHolder.setContext(newContext);
            filterChain.doFilter(request, response);
        }catch (AuthenticationException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-type", "text/plain;charset=utf-8");
            response.getWriter().println(e.getMessage());
            return;

        }

        if ("beep-boop".equals(password))
        {
            //OK


        }






    }
}
