package com.tim4.app.account.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tim4.app.account.helpers.AuthorizeToken;
import com.tim4.app.account.models.Login;
import com.tim4.app.account.models.Token;
import com.tim4.app.account.models.User;
import com.tim4.app.account.repositories.UsersRepository;
import com.tim4.app.account.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")
@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
@RestController
@RequestMapping(value = "/users")
public class UsersController {

    private final UserService userService;
    private final UsersRepository usersRepository;
    private final AuthorizeToken authorizeToken;

    @Autowired
    public UsersController(UsersRepository usersRepository, UserService userService){
        this.usersRepository = usersRepository;
        this.userService = userService;
        this.usersRepository.deleteAll();
        this.authorizeToken = new AuthorizeToken(usersRepository);
        saveSome();
        }

    private void saveSome() {
        User belma = new User("Belma", "Homarac", "belma123@gmail.com", "belma123", "Belma123", 22, 2);
        usersRepository.save(belma);
        User emina = new User("Emina", "Omanovic", "emina123@gmail.com", "emina123", "Emina123", 22, 1);
        usersRepository.save(emina);
        User sajra = new User("Sajra", "Muratovic", "sajra@gmail.com", "sajra123", "Sajra123", 22, 1);
        usersRepository.save(sajra);
        User sunita = new User("Sunita", "Imamovic", "sunita@gmail.com", "sunita123", "Sunita123", 22, 1);
        usersRepository.save(sunita);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<?> all(@RequestHeader Token token){

        AuthorizeToken authorizeToken = new AuthorizeToken(usersRepository);

        if (authorizeToken.authorizeUser(token)) {

            DecodedJWT jwt = JWT.decode(token.getToken());
            User u =  usersRepository.findByRole(jwt.getClaim("role").asInt());
            try {
                if (u.getRole().equals(2)) {
                    return userService.getAllUsersByRole(1);
                }
                else if(u.getRole().equals(1)){
                    return new ResponseEntity<String>("Not authorized user", HttpStatus.UNAUTHORIZED);
                }
            }
            catch (Exception e){
                return new ResponseEntity<String>("Not authorized user", HttpStatus.UNAUTHORIZED);
            }
        }

        return new ResponseEntity<String>("Not authorized user", HttpStatus.UNAUTHORIZED);
    }



    @RequestMapping(method = RequestMethod.GET, value = "/get/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        return userService.getUser(username);
    }


    @RequestMapping(method = RequestMethod.POST, value="/user/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/edit/{username}")
    public ResponseEntity<?> updateUser(@Valid @PathVariable("username") String username, @RequestBody User user) {
        return userService.updateUser(username, user);
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.DELETE}, value = "/delete/{username}")
    public ResponseEntity<?> deleteUser(@RequestHeader Token token, @PathVariable  String username) {
        if(authorizeToken.authorizeUser(token)){
            DecodedJWT jwt = JWT.decode(token.getToken());
            User u =  usersRepository.findByUsername(jwt.getClaim("username").asString());
            if(u.getRole().equals(2)){
                return userService.deleteUser(username);
            }
        }
        return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.DELETE}, value = "/delete")
    public ResponseEntity<?> deleteAll(){
        return userService.deleteAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all/feign")
    public List<User> allFeign(){
        return userService.getAllFeign();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public Optional<User> getUserFeign(@RequestParam Integer id) {
        return userService.getUserFeign(id);
    }

    @RequestMapping(method = RequestMethod.POST, value="/login")
    public ResponseEntity<?> loginUser(@RequestBody Login login) {
        return userService.loginUser(login.getUsername(), login.getPassword());
    }

    @RequestMapping(method = RequestMethod.GET, value="/getUserProfile")
    public ResponseEntity<?> getUserProfile(@RequestHeader Token token) {

        AuthorizeToken authorizeToken = new AuthorizeToken(usersRepository);

        if (authorizeToken.authorizeUser(token))
           return userService.getProfile(token);

        return new ResponseEntity<String>("Not authorized user", HttpStatus.UNAUTHORIZED);
    }
}


