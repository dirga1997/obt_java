package com.ait.project.template.config.security.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtUtil {
    private static String SECRET_KEY = "q3t6w9zCFJNcQfTjWnZr4u7xADGKaPdSgUkXp2s5v8yBEHMbQeTh";

    public String extractUsername(String token) {
        System.out.println("tokk");
        System.out.println(token);
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        System.out.println("aaa");
        System.out.println(token);
        System.out.println(claimsResolver);
        final Claims claims = extractAllClaims(token);
        System.out.println("claims");
        System.out.println(claims);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        System.out.println("a");
        System.out.println(userDetails.getUsername());
        Map<String, Object> claims = new HashMap<>();
        System.out.println("b");
        System.out.println(claims);
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        System.out.println("c: " + Jwts.builder() );
        System.out.println("c: " + Jwts.builder().setClaims(claims) );
        System.out.println("c: " + Jwts.builder().setClaims(claims).setSubject(subject) );
        System.out.println("c: " + Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())) );
        System.out.println("c: " + Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) );
        System.out.println("c: " + Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) );
        String jwt = Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
        System.out.println("d");
        System.out.println(jwt);
        return  jwt;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
