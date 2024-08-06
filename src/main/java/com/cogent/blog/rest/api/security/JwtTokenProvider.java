package com.cogent.blog.rest.api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private String jwtSecret = "sdhfskjdfiosdfoisdjfiosjdfdsoifjsdoifjdsofij";
    private Long jwtExpirationDate = 60480L;

    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpirationDate);
        String token = Jwts.builder().subject(username).issuedAt(currentDate).expiration(expirationDate).signWith(key())
                .compact();
        return token;}

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtSecret));
    }

    public String getUsername(String token){
        return Jwts.parser().verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);
            return true;
        }catch (MalformedJwtException e){
            e.printStackTrace();
        } catch (ExpiredJwtException e){
            e.printStackTrace();
        } catch (UnsupportedJwtException e){
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return false;
    }
}
