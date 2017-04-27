package com.example.security.filter;

import com.example.security.AuthenticationToken;
import com.example.security.TokenService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    public AuthenticationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain
    ) throws ServletException, IOException
    {
        String token = httpServletRequest.getHeader("X-Authorization");

        if(token != null) {
            int userIdFromToken = tokenService.getUserIdFromToken(token);
            AuthenticationToken authenticationToken = new AuthenticationToken(token, userIdFromToken);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
