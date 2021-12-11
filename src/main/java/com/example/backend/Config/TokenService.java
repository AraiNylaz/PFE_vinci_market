package com.example.backend.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend.model.UserDTO;
import com.example.backend.services.User.UserService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class TokenService {

    private CustomProperties cs;
    private final Algorithm jwtAlgorithm;
    private final JWTVerifier jwtVerifier;
    private UserService userService;

    public TokenService(CustomProperties cs,UserService userService) {
        this.cs = cs;
        this.userService=userService;
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

    public String decode(String token){
        DecodedJWT tok = jwtVerifier.verify(token);
        return tok.getClaim("user").asString();
    }

    public boolean verifyTokenAndAdmin(String token){
        try{
            jwtVerifier.verify(token);
            String idUser=decode(token);
            UserDTO u=userService.findOneById(new ObjectId(String.valueOf(idUser)));
            if(!u.isAdmin()){
                return false;
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
