package com.hakim.datauploder.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    public static void commitTokens(HttpServletResponse response, UserDetails userDetails) throws IOException {

        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", userDetails.getAuthorities());

        String accessToken = JwtHelper.generateToken(userDetails, claims, (1000 * 60 * 60 * 24));
        String refreshToken = JwtHelper.generateToken(userDetails, new HashMap<>(), (1000 * 60 * 60 * 24 * 7));

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("access-token", accessToken);
        tokenMap.put("refresh-token", refreshToken);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),tokenMap);
    }

    public static void commitResponse(HttpServletResponse response, Map<String,String> map) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),map);
    }
}
