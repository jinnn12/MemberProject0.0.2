package com.cash.memberProject002.Auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.io.IOException;
import java.util.ArrayList;

// 대전제 : Authentication 객체를 만들기 위함

@Component
@Slf4j
public class JwtTokenFilter extends GenericFilter {
    @Value("${jwt.secretKeyAt}")
    private String secretKey;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String bearerToken = req.getHeader("Authorization");
//        401에러 (UnAuthorized, Authentication: 인증, 인증되지 않음)
//        403에러 (Forbidden, Authorization: 인가, 권한없음)
//        토큰이 없는 경우 chain으로 돌아가라
        try {
            if (bearerToken == null) {
                chain.doFilter(request, response);
                return;
            }

            String token = bearerToken.substring(7);
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + claims.get("role")));

            Authentication authentication = new UsernamePasswordAuthenticationToken(claims.getSubject(), "", authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        chain.doFilter(request, response);
    }
}
