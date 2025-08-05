package com.learnerNrunnerBE.learnerNrunnerBE.global.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.*;

@Slf4j
@Component
public class JwtUtil implements InitializingBean {
    private static final String AUTHORITIES_KEY = "auth";

    @Value("${jwt.access-token-expiration-time}")
    private long accessTokenExpirationTime;

    @Value("${jwt.refresh-token-expiration-time}")
    private long refreshTokenExpirationTime;

    @Value("${jwt.secret}")
    private String secretKey;

    private SecretKey key;

    @Override
    public void afterPropertiesSet(){
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String createToken(String subject, String type, long expirationTime, String authorities){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime);

        JwtBuilder builder = Jwts.builder()
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256) // 서명 방식(해시/생성)
                .claim("type", type); //커스텀 claim 필요하면 identifier 정도 추가(프론트ㅔ서 빠른 식별 필요시)

        if (subject != null) {
            builder.setSubject(subject);
        }
        if (authorities != null) {
            builder.claim(AUTHORITIES_KEY, authorities);
        }

        return builder.compact();
    }

    public String generateAccessToken(Authentication authentication) {
        return createToken(authentication.getName(), "access", accessTokenExpirationTime, null);
    }

    public String generateAccessToken(String email){ //회원가입 용
        return createToken(email, "access", accessTokenExpirationTime, null);
    }

    public String generateRefreshToken() {
        return createToken(null, "refresh", refreshTokenExpirationTime, null);
    }

    //role 권한 필요시 추가
//    private String getAuthorities(Authentication authentication){
//        return authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining());
//    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

//        if (claims.get(AUTHORITIES_KEY) == null) {
//            throw new RuntimeException("Token does not contain authority information");
//        } // role 권한 필요시 추가

        // 권한 기본값을 부여 (role 권한 필요시 수정)
        Collection<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

        // 사용자 ID를 subject로부터 사용
        UserDetails principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    // claim 받아오기 (서명 키 비교 후)
    public Claims parseClaims(String token){
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }

    public Boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e){
            log.info("Expired JWT token : {}", e.getMessage());
        } catch (UnsupportedJwtException e){
            log.info("Unsupported JWT token : {}", e.getMessage());
        } catch (MalformedJwtException e){
            log.info("Invalid JWT token : {}", e.getMessage());
        } catch (SecurityException | IllegalArgumentException e){
            log.info("JWT claims string is empty or signature does not match.");
        }
        return false;
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length()).trim();
        }
        return null;
    }

}
