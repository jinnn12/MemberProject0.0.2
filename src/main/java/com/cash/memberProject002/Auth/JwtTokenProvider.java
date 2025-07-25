package com.cash.memberProject002.Auth;

import com.cash.memberProject002.author.domain.Author;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.experationAt}")
    private int experationAt;
    @Value("${jwt.secretKeyAt}")
    private String secretKeyAt;

    private Key secret_at_key;

    @PostConstruct
    public void init() {
        secret_at_key = new SecretKeySpec(java.util.Base64.getDecoder().decode(secretKeyAt), SignatureAlgorithm.HS512.getJcaName());
    }

    public String createAtToken (Author author) {
        String email = author.getEmail();
        String role = author.getRole().toString();

        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);

        Date now = new Date();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + experationAt*60*1000L))
                .signWith(secret_at_key)
                .compact();

        return token;
    }


}
