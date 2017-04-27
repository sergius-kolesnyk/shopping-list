package com.example.security;

import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public int getUserIdFromToken(String token) {
        //todo check if token is valid
        return 1;
    }
}
