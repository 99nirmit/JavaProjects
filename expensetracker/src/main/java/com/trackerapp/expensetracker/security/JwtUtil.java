package com.trackerapp.expensetracker.security;

import io.jsonwebtoken. *;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import io.jsonwebtoken.io.Decoders;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${spring.app.jwtSecret}")
    private String jwtSecret;

    @Value(("${spring.app.jwtExpirationsMs}"))
    private int jwtExpirationsMs;

    private SecretKey key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String extractUsername(String token){
        return extractAllClaim(token).getSubject();
    }

    private Claims extractAllClaim(String token){
        return Jwts.parser()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getPayload();
    }

    public String generateToken(UserDetails userDetails){
        String username = userDetails.getUsername();
        Map<String, Object> claims = new HashMap<>();
        return generateTokenFromUserName(claims, username);
    }

    private String generateTokenFromUserName(Map<String, Object> claims, String username){
        return Jwts.builder()
                .claims(claims)
                .setSubject(username)
                .header().empty().add("typ", "jwt")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date((new Date()).getTime() + jwtExpirationsMs))
                .signWith(key())
                .compact();
    }

    public boolean validateJwtToken(String token){
        try{
            System.out.println("Validate");
            Jwts.parser()
                    .setSigningKey(key())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        } catch(JwtException e){
            throw new RuntimeException("JWT Validation Failed: " + e.getMessage());
        }
    }

    public String getJwtFromHeader(HttpServletRequest request){
        System.out.println(request + " Getting request");
        String bearerToken = request.getHeader("Authorization");
        System.out.println(bearerToken);
        logger.debug("Authorization Header: {}", bearerToken);
        if(bearerToken != null && bearerToken.startsWith("Bearer")){
            return bearerToken.substring(7);
        }
        return null;
    }


}
