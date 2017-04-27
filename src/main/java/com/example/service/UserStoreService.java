package com.example.service;

import org.springframework.security.core.userdetails.User;

public interface UserStoreService {
    boolean isUserLoggedIn(int id, String token);
    User getUserData(int id);
}
