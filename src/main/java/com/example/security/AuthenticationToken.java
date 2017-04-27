package com.example.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

public class AuthenticationToken extends AbstractAuthenticationToken {
    private User authenticatedUser;
    private String token;
    private Integer id;

    public AuthenticationToken(String token, Integer id){
        super(Collections.emptyList());
        this.token = token;
        this.id = id;
    }

    public AuthenticationToken(Collection<? extends GrantedAuthority> authorities, User authenticatedUser, String token, Integer id) {
        super(authorities);
        this.authenticatedUser = authenticatedUser;
        this.token = token;
        this.id = id;
    }

    public User getAuthenticatedUser() {
        return authenticatedUser;
    }

    public void setAuthenticatedUser(User authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }

    @Override
    public Object getCredentials() {
        return authenticatedUser.getPassword();
    }

    @Override
    public Object getPrincipal() {
        return authenticatedUser;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
