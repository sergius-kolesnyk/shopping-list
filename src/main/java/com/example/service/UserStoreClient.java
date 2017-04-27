package com.example.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserStoreClient implements UserStoreService {
    public boolean isUserLoggedIn(int id, String token) {
        if(id == 1 && token.equals("token1")) {
            return true;
        }

        return false;
    }

    public User getUserData(int userId) {
        switch (userId) {
            case 1:
                return new User("Sergey", "secret", Collections.emptyList());
            default:
                return null;
        }
    }
}
