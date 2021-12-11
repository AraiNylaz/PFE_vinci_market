package com.example.backend.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.backend.model.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class TokenService {

    private CustomProperties cs;
    private final Algorithm jwtAlgorithm;
    private final JWTVerifier jwtVerifier;

    public TokenService(CustomProperties cs) {
        this.cs = cs;
        jwtAlgorithm=Algorithm.HMAC256(cs.getJwtSecret());
        jwtVerifier= JWT.require(this.jwtAlgorithm).withIssuer("auth0").build();
    }

    public String createToken(UserDTO userDTO){
        return JWT.create().withIssuer("auth0").withClaim("user",userDTO.getIdUser()).sign(jwtAlgorithm);
    }

    public boolean verifyToken(String token){
        try{
            jwtVerifier.verify(token);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
