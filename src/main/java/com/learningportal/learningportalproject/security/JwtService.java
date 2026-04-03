package com.learningportal.learningportalproject.security;

import com.learningportal.learningportalproject.user.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private Key key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserEntity userEntity) {
        Instant now = Instant.now();

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userEntity.getRole().name());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userEntity.getUserName())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(jwtExpiration)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims != null ? claims.getSubject() : null;
    }

    public boolean isTokenValid(String token, String username) {

        if (token == null || username == null)
            return false;

        Claims claims = extractAllClaims(token);
        if (claims == null)
            return false;

        return claims.getSubject() != null
                && username.equals(claims.getSubject())
                && !isExpired(claims);
    }

    private boolean isExpired(Claims claims) {
        return claims.getExpiration().before(Date.from(Instant.now()));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
