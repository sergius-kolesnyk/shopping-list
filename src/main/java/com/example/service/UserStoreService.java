package com.example.service;

import com.example.domain.UserDetails;

public interface UserStoreService {
    boolean isUserLoggedIn(int id, String token);
    UserDetails getUserData(int id);
}
