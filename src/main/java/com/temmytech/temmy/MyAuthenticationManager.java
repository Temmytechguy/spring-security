package com.temmytech.temmy;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * @author TemmyTechie
 */

public class MyAuthenticationManager  implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var username = authentication.getName();
        if("temmy".equals(username)){
            //
            return UsernamePasswordAuthenticationToken.authenticated(
                    "temmy",
                    null,
                    AuthorityUtils.createAuthorityList("ROLE_ADMIN")
            );
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
