package com.blog.blogspot.util;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final Key SIGNING_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SIGNING_KEY)
                .compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, String username){
        final String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token){
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody();
    }

}
