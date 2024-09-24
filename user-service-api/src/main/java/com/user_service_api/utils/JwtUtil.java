package com.user_service_api.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.user_service_api.models.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String JWT_SECRET;

    @Value("${jwt.expiration}")
    private Long JWT_EXPIRATION;


    public String generateToken(User user) {
 
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(System.currentTimeMillis() + JWT_EXPIRATION);


        Map<String, Object> claims = generateClaims(user);


        SecretKey key = getKey();
        MacAlgorithm signatureAlgorithm = Jwts.SIG.HS256;

        return Jwts
                .builder()
                .header()
                .type("JWT")
                .and()
                .subject(user.getEmail())
                .claims(claims)
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(key, signatureAlgorithm)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        Boolean isExpired = isTokenExpired(token);

        return username.equals(userDetails.getUsername()) && !isExpired;
    }


    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }


    private Date getExpirationFromToken(String token) {
        return getClaim(token, Claims::getExpiration);
    }


    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
    }

    private Map<String, Object> generateClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", user.getId());
        claims.put("role", user.getRole().name());

        return claims;
    }


    private <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        Claims payload = Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claimsResolver.apply(payload);
    }


    private Boolean isTokenExpired(String token) {
        return getExpirationFromToken(token).before(new Date());
    }
}
