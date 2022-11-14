package com.assignments.org.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenGenerator {
    public String generateToken(Authentication authentication){
        String username=authentication.name();
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
    public Boolean validateToken(){
        try{
            Jwts.parser().setSigningKey(SecurityConstants.)
        }
    }
}
