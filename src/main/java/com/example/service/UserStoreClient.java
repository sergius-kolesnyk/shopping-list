package com.example.service;

import com.example.domain.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserStoreClient implements UserStoreService {
    public boolean isUserLoggedIn(int id, String token) {
        return id == 1 && token.equals("token1") || id == 2 && token.equals("token2");
    }

    public UserDetails getUserData(int userId) {
        switch (userId) {
            case 1:
                return new UserDetails(1, "Wife");
            case 2:
                return new UserDetails(2, "Husband");
            default:
                return null;
        }
    }
}
