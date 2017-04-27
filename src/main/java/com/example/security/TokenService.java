package com.example.security;

import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public int getUserIdFromToken(String token) throws Exception {
        //todo check if token is valid

        switch (token) {
            case "token1":
                return 1;
            case "token2":
                return 2;
        }
        throw new Exception("Not valid token: " + token);
    }
}
