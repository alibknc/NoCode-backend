package com.kou.bitirme.util;

import com.kou.bitirme.data.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "yourSecretKey";

    public String generateToken(String id) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, id);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .signWith(HS512, "dlesalathglarxit")
                .compact();
    }

    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token, User userDetails) {
        final String id = extractUserId(token);
        return id.equals(userDetails.getId().toString());
    }

}