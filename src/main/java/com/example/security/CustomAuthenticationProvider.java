package com.example.security;

import com.example.service.UserStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserStoreService userStoreService;

    @Autowired
    public CustomAuthenticationProvider(UserStoreService userStoreService) {
        this.userStoreService = userStoreService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AuthenticationToken authenticationToken = (AuthenticationToken) authentication;
        boolean userLoggedIn = userStoreService.isUserLoggedIn(authenticationToken.getId(), authenticationToken.getToken());
        if(userLoggedIn) {
            User userData = userStoreService.getUserData(authenticationToken.getId());
            authenticationToken.setAuthenticatedUser(userData);
            return authenticationToken;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
