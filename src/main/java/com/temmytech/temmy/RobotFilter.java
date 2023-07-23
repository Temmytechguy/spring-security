package com.temmytech.temmy;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author TemmyTechie
 */

public class RobotFilter extends OncePerRequestFilter {

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var  password = request.getHeader("X-robot-password");
        if ("beep-boop".equals(password))
        {
            //OK
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
    }
}
