package com.tim4.app.account.helpers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tim4.app.account.models.Token;
import com.tim4.app.account.models.User;
import com.tim4.app.account.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AuthorizeToken {

    private final UsersRepository usersRepository;

    @Autowired
    public AuthorizeToken(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public boolean authorizeUser(Token token) {

        User user = new User();
        boolean success = false;

        try {
            DecodedJWT jwt = JWT.decode(token.getToken());
            user = usersRepository.findByUsernameAndRole(jwt.getClaim("username").asString(), jwt.getClaim("role").asInt());
            success =  true;
        } catch (JWTDecodeException exception){
        }

        return success;

    }


}
