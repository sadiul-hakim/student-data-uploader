package com.hakim.datauploder.security;

import com.hakim.datauploder.service.CustomUserDetailsService;
import com.hakim.datauploder.util.JwtHelper;
import com.hakim.datauploder.util.ResponseUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getServletPath().equalsIgnoreCase("/login") || request.getServletPath().equalsIgnoreCase("/refreshToken")) {
            filterChain.doFilter(request, response);
        } else {

            String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authorization != null && authorization.startsWith("Bearer ")) {
                try {
                    String token = authorization.substring("Bearer ".length());
                    String username = JwtHelper.extractUsername(token);

                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

                    if (JwtHelper.isValidToken(token, userDetails) && SecurityContextHolder.getContext().getAuthentication() == null) {

                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                         IllegalArgumentException ex) {

                    // If the token is Invalid send an error with the response
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put("error", "Invalid Token");
                    ResponseUtil.commitResponse(response, errorMap);
                }
            }

            filterChain.doFilter(request, response);
        }
    }
}
