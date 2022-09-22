package com.food.auth.provider;

import com.food.auth.provider.dto.AccessToken;
import com.food.auth.provider.dto.AccessTokenContent;
import com.food.auth.provider.dto.AccessTokenValidationResult;
import com.food.auth.utils.DateUtils;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class JwtProvider implements AccessTokenProvider {
    private final String SECRET_KEY = "testKey";

    @Override
    public AccessToken create(Long userId) {
        LocalDateTime issuedDate = LocalDateTime.now();
        LocalDateTime expiredDate = issuedDate.plusMinutes(10);

        Claims claims = Jwts.claims().setSubject(String.valueOf(userId));

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(DateUtils.toDate(issuedDate))
                .setExpiration(DateUtils.toDate(expiredDate))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        return new AccessToken(accessToken);
    }

    @Override
    public AccessTokenValidationResult validate(AccessToken accessToken) {
        try {
            Jws<Claims> claimsJws = parseClaimsJws(accessToken);
            validateExpiration(claimsJws.getBody());

            return AccessTokenValidationResult.success();
        } catch (SignatureException | MalformedJwtException e) {
            return AccessTokenValidationResult.fail("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            return AccessTokenValidationResult.fail("만료된 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            return AccessTokenValidationResult.fail("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            return AccessTokenValidationResult.fail("JWT 토큰이 잘못되었습니다.");
        }
    }

    @Override
    public AccessTokenContent getContent(AccessToken token) {
        Jws<Claims> claimsJws = parseClaimsJws(token);

        String userId = claimsJws.getBody().getSubject();
        return new AccessTokenContent(Long.valueOf(userId));
    }

    private void validateExpiration(Claims body) {
        Date expiration = body.get("exp", Date.class);

        if (expiration.before(new Date())) {
            throw new ExpiredJwtException(null, null, null);
        }
    }

    private Jws<Claims> parseClaimsJws(AccessToken token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .setAllowedClockSkewSeconds(300L)
                .parseClaimsJws(token.getValue());
    }

}
