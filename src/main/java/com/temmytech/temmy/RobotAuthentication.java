package com.temmytech.temmy;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author TemmyTechie
 */

public class RobotAuthentication implements Authentication {

    private boolean isAuthenticated = true;
    private List<GrantedAuthority> authorities;
    private final  String password;

    public RobotAuthentication(List<GrantedAuthority> authorities, String password) {
        this.authorities = authorities;
        this.password = password;
        this.isAuthenticated = password == null;
    }

    public static RobotAuthentication unAuthenticated(String password)
    {
        return new RobotAuthentication((Collections.emptyList()), password);
    }

    public static RobotAuthentication authenticated(String password)
    {
        return new RobotAuthentication(AuthorityUtils.createAuthorityList("ROLE_robot"), null);
    }

    @Override
    public String getName() {
        return " This is Robot";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return getName();
    }

    @Override
    public boolean isAuthenticated() {

        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("Dont");
    }

    public String getPassword()
    {

        return password;
    }
}
