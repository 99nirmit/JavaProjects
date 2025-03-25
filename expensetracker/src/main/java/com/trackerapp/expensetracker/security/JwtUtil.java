package com.trackerapp.expensetracker.security;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "";

    public String extractUsername(String token){
        return extractUsername(token, claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Class, T> claimsRemover){
        final Claims = extractAllClaim(token);
        return  claimsRemover.apply(claims);
    }

    private Claims
}
