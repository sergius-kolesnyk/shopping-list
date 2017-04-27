package com.example.security.filter;

import com.example.domain.UserDetails;
import com.example.security.AuthenticationInfo;
import com.example.security.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
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
            int userIdFromToken = 0;
            try {
                userIdFromToken = tokenService.getUserIdFromToken(token);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

            UserDetails userDetails = new UserDetails();
            userDetails.setId(userIdFromToken);

            AuthenticationInfo authenticationInfo = new AuthenticationInfo(userDetails, token);
            SecurityContextHolder.getContext().setAuthentication(authenticationInfo);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
