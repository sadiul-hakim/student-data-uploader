package com.hakim.datauploder.controller;

import com.hakim.datauploder.service.CustomUserDetailsService;
import com.hakim.datauploder.util.JwtHelper;
import com.hakim.datauploder.util.ResponseUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {


        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization != null && authorization.startsWith("Bearer ")) {
            try {
                String token = authorization.substring("Bearer ".length());
                String username = JwtHelper.extractUsername(token);

                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

                if (JwtHelper.isValidToken(token, userDetails)) {

                    Map<String, Object> claims = new HashMap<>();
                    claims.put("authorities", userDetails.getAuthorities());

                    String accessToken = JwtHelper.generateToken(userDetails, claims, (1000 * 60 * 60 * 24));

                    Map<String, String> tokenMap = new HashMap<>();
                    tokenMap.put("access-token", accessToken);
                    ResponseUtil.commitResponse(response, tokenMap);
                }
            } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                     IllegalArgumentException ex) {

                // If the token is Invalid send an error with the response
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("error", "Invalid Token");
                ResponseUtil.commitResponse(response, errorMap);
            }
        } else {
            throw new RuntimeException("Refresh token is missing.");
        }
    }
}
