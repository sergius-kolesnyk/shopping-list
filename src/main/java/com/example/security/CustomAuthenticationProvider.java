package com.example.security;

import com.example.domain.UserDetails;
import com.example.service.UserStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
        AuthenticationInfo authenticationInfo = (AuthenticationInfo) authentication;
        boolean userLoggedIn = userStoreService.isUserLoggedIn(authenticationInfo.getDetails().getId(), authenticationInfo.getCredentials());
        if(userLoggedIn) {
            UserDetails userData = userStoreService.getUserData(authenticationInfo.getDetails().getId());

            authenticationInfo.setUserDetails(userData);
            return authenticationInfo;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
