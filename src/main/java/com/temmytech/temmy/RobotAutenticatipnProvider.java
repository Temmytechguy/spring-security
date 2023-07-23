package com.temmytech.temmy;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.List;

/**
 * @author TemmyTechie
 */

public class RobotAutenticatipnProvider  implements AuthenticationProvider {

    private final List<String> passwords;

    public RobotAutenticatipnProvider(List<String> password) {
        this.passwords = password;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var authRequest = (RobotAuthentication) authentication;
        var password = authRequest.getPassword();

        if(!passwords.contains(password))
        {
            throw new BadCredentialsException("You are not misses robot");
        }

        return RobotAuthentication.authenticated(password);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return RobotAuthentication.class.isAssignableFrom(authentication);
    }
}
