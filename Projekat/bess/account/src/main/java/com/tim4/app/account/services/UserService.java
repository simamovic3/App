package com.tim4.app.account.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tim4.app.account.exceptions.Exceptions;
import com.tim4.app.account.models.Token;
import com.tim4.app.account.models.User;
import com.tim4.app.account.repositories.UsersRepository;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private final UsersRepository usersRepository;
    private final RabbitTemplate rabbitTemplate;
    private final Exchange exchange;

    @Autowired
    public UserService(RabbitTemplate rabbitTemplate, Exchange exchange, UsersRepository usersRepository){
        this.usersRepository = usersRepository;
        this.usersRepository.deleteAll();
        this.exchange = exchange;
        this.rabbitTemplate = rabbitTemplate;

    }

    public ResponseEntity<?> getAllUsers(){

        Collection<User> users = usersRepository.findAll();

        if(users.isEmpty())
            return new Exceptions().NoUsersInDatabase();

        return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);

    }

    public ResponseEntity<?> getAllUsersByRole(Integer role){

        Collection<User> users = usersRepository.findUsersByRole(role);

        if(users.isEmpty())
            return new Exceptions().NoUsersInDatabase();

        return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);

    }

    public ResponseEntity<?> getUser(String username) {

        User userFromDatabase = null;

        userFromDatabase = usersRepository.findByUsername(username);

        if (userFromDatabase == null)
            return new Exceptions().UserNotFound(username);

        return new ResponseEntity<User>(userFromDatabase, HttpStatus.OK);
    }

    public ResponseEntity<?> addUser(User user) {
        usersRepository.save(user);
        Collection<User> users = usersRepository.findAll();
        return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
    }

    public ResponseEntity<?> updateUser(String username, User user) {

        User userFromDatabase = null;

        userFromDatabase = usersRepository.findByUsername(username);

        if (userFromDatabase == null)
            return new Exceptions().UserNotFound(username);

        userFromDatabase.setName(user.getName());
        userFromDatabase.setLastName(user.getLastName());
        userFromDatabase.setAge(user.getAge());

        usersRepository.save(userFromDatabase);
        return new ResponseEntity<User>(userFromDatabase, HttpStatus.OK);
    }


    public ResponseEntity<?> deleteUser(String username) {

        User userFromDatabase = usersRepository.findByUsername(username);

        if (userFromDatabase == null)
            return new Exceptions().UserNotFound(username);

     /*   String routingKey = "user.deleted";
        String message = "user deleted";

        rabbitTemplate.convertAndSend(exchange.getName(), routingKey, message);

*/
        usersRepository.deleteById(userFromDatabase.getUserId());

        Collection<User> users = usersRepository.findUsersByRole(1);

        return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteAll(){
        Collection<User> usersFromDatabase = usersRepository.findAll();

        if (!usersFromDatabase.isEmpty())
            return new Exceptions().NoUsersInDatabase();

        usersRepository.deleteAll();
        Collection<User> users = usersRepository.findAll();
        return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
    }
    public List<User> getAllFeign(){

        List<User> users = usersRepository.findAll();

        return users;

    }
    public Optional<User> getUserFeign(Integer userId) {

        Optional<User> user = usersRepository.findById(userId);

        return user;
    }

    public ResponseEntity<?> loginUser(String username, String password){

        boolean userExists = false;
        String token = "";

        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");

            User user = this.usersRepository.findByUsername(username);

            if(user.getPassword().equals(password)) {
                userExists = true;
                token = JWT.create()
                        .withClaim("id", user.getUserId())
                        .withClaim("username", user.getUsername())
                        .withClaim("role", user.getRole())
                        .sign(algorithm);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        if(userExists)
            return new ResponseEntity<Token>(new Token(token), HttpStatus.OK);

        return new ResponseEntity<String>("Username or password is incorrect", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getProfile(Token token) {

        User user = new User();
        boolean success = false;

        try {
            DecodedJWT jwt = JWT.decode(token.getToken());
            user = usersRepository.findByUsername(jwt.getClaim("username").asString());
            success =  true;
        } catch (JWTDecodeException exception){
        }

        if(success)
          return new ResponseEntity<User>(user, HttpStatus.OK);

        return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);

    }


}
