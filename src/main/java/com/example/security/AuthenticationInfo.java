package com.example.security;

import com.example.domain.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class AuthenticationInfo implements Authentication {
    private UserDetails userDetails;
    private String token;

    public AuthenticationInfo(UserDetails userDetails, String token) {
        this.userDetails = userDetails;
        this.token = token;
    }

    public AuthenticationInfo(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getCredentials() {
        return token;
    }

    @Override
    public UserDetails getDetails() {
        return userDetails;
    }

    @Override
    public UserDetails getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
