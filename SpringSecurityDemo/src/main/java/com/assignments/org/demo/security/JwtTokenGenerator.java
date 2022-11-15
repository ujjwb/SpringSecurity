package com.assignments.org.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenGenerator {
    public String generateToken(Authentication authentication){
        String username=authentication.getName();
        Date currentDate=new Date();
        Date expireDate=new Date(currentDate.getTime()+SecurityConstants.JWT_EXPIRATION);
        String token= Jwts.builder().setSubject(username)
                .setIssuedAt(new Date()).setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,SecurityConstants.JWT_SECRET)
                .compact();
        return token;
    }
    public String getUserNameFomJwt(String token){
        Claims claims=Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public Boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token);
            return true;
        }
        catch(Exception e){
            throw new AuthenticationCredentialsNotFoundException("JWT is expired or incorrect");
        }
    }
}
