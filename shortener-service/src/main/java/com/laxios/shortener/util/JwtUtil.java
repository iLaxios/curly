package com.laxios.shortener.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class JwtUtil {

    private static String jwtSecret;

    public JwtUtil(@Value("${jwt.secret:LifeIsNotAllSunshineAndRosesSoKeepCalmAnd69}") String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public static String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret.getBytes())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
