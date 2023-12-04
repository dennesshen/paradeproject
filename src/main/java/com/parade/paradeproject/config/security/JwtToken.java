package com.parade.paradeproject.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.security.auth.message.AuthException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author christinehsieh on 2023/5/3
 */
@Component
public class JwtToken {

    private static final long EXPIRATIONTIME = 1*60*60*1000 ; // 1 hour

    private static final String Security = "parade";

    //簽發 JWT TOKEN
    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(Instant.now().toEpochMilli() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS256, Security)
                .compact();
    }

    //驗證 JWT TOKEN
    public boolean validateToken(String token) throws AuthException {
        try {
            Jwts.parser()
                .setSigningKey(Security)
                .parseClaimsJws(token);
        } catch (Exception e) {
            throw new AuthException("Invalid JWT signature.");
        }
        return true;
    }


    public String generateToken(Authentication authentication) {

        ParadeUser user = (ParadeUser)authentication.getPrincipal();

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("userId", user.getUserId());
        claims.put("authorities", user.getAuthorities());

        return generateToken(claims);
    }

    public UsernamePasswordAuthenticationToken toAuthentication(String jwt) {

        Claims claims = Jwts.parser().setSigningKey(Security).parseClaimsJws(jwt).getBody();

        String username = claims.get("username").toString();
        Long userId = Long.parseLong(claims.get("userId").toString());

        List<Map<String, String>> authoritiesClaim =
                (List<Map<String, String>>) claims.get("authorities");

        List<GrantedAuthority> authorities =
                authoritiesClaim.stream()
                        .map(item -> new ParadeAuthority(item.get("authority")))
                        .collect(Collectors.toList());


        UserDetails principal = new ParadeUser(username, "", authorities, userId);
        return new UsernamePasswordAuthenticationToken(principal, null, authorities);


    }
}
