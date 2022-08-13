package com.food.auth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtTokenService {

    private String secretKey = "test";
    private final Long TOKEN_VALID_TIME = 10 * 60 * 1000L; //10분
    private final String USER_ID = "userId";

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(Long userId) {
        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + TOKEN_VALID_TIME);

        return Jwts.builder()
                .claim(USER_ID, userId)
                .setIssuedAt(now)
                .setExpiration(expirationTime)
                .signWith(SignatureAlgorithm.ES256, secretKey)
                .compact();
    }


}
