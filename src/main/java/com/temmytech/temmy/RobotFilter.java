package com.temmytech.temmy;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
<<<<<<< HEAD
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
=======
>>>>>>> 002081c (first commit)
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
<<<<<<< HEAD
import java.util.Collections;
=======
>>>>>>> 002081c (first commit)

/**
 * @author TemmyTechie
 */

public class RobotFilter extends OncePerRequestFilter {

<<<<<<< HEAD
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(!Collections.list(request.getHeaderNames()).contains("x-robot-passowrd"))
        {
            filterChain.doFilter(request, response);
            return;
        }

        //1. Authentication Decision

=======
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
>>>>>>> 002081c (first commit)
        var  password = request.getHeader("X-robot-password");
        if ("beep-boop".equals(password))
        {
            //OK
<<<<<<< HEAD
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-type", "text/plain;charset=utf-8");
            response.getWriter().println("You are not Ms Robot");
            return;

        }



        var newContext = SecurityContextHolder.createEmptyContext();
        newContext.setAuthentication(new RobotAuthentication());
        SecurityContextHolder.setContext(newContext);
        filterChain.doFilter(request,response);


=======
            var newContext = SecurityContextHolder.createEmptyContext();
            newContext.setAuthentication(????);
            SecurityContextHolder.setContext(newContext);
            filterChain.doFilter(request,response);
            return;
        }
        else{
            // No no
        }

        filterChain.doFilter(request, response);
>>>>>>> 002081c (first commit)
    }
}
