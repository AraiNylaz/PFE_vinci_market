package com.example.backend.controllers;

import com.example.backend.Config.TokenService;
import com.example.backend.model.User;
import com.example.backend.model.UserDTO;
import com.example.backend.services.User.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private TokenService tokenService;

    public UserController(UserService userService,TokenService tokenService) {
        this.userService = userService;
        this.tokenService=tokenService;
    }

    @GetMapping
    public Iterable<User> allUser(){
        return userService.findAllUsers();
    }

    /*@PostMapping
    public ResponseEntity<Object> addUser (@RequestBody User user){
        User u = userService.saveUser(user) ;
        if(u==null) return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(u.getIdUser())
                .toUri() ;
        return  ResponseEntity.created(location).build();
    }*/

    /*@PostMapping
    public User addUser (@RequestBody User user) {
        System.out.println("add user " + user);
        User u=null;
        try{
            user.setCampusName(user.getCampus().getName());
            u= userService.saveUser(user);
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return u;
    }*/

    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody User user){
        User u=null;
        try{
            user.setCampusName(user.getCampus().getName());
            u= userService.saveUser(user);
            UserDTO userDTO=new UserDTO(user.getIdUser(), u.getLastName(), u.getFirstName(), user.getCampus(), user.getCampusName(), user.getPhone(), user.getMail(), user.isAdmin(), user.isBan());
            String token=tokenService.createToken(userDTO);
            URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDTO.getIdUser()).toUri();
            return ResponseEntity.created(location).header("Authori",token).build();
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public User checkUser(@RequestBody User user){
        return userService.checkUser(user);
    }

    @GetMapping("/mail")
    public User getUserByMail(@RequestParam String mail){
        return userService.findOneByMail(mail);
    }

    @GetMapping("/detail/{id}")
    public User getUserById(@PathVariable String id){
        return userService.findOneById(new ObjectId(String.valueOf(id)));
    }

    @GetMapping("/role/{id}")
    public void switchRole(@PathVariable String id){
        userService.switchRole(new ObjectId(String.valueOf(id)));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id,@RequestBody User user){
       return userService.updateUser(new ObjectId(String.valueOf(id)),user);
    }

    @GetMapping("/delete/{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUser(new ObjectId(String.valueOf(id)));
    }

    @GetMapping("/user/{id}")
    public void switchBan(@PathVariable String id){
        userService.switchBan(new ObjectId(String.valueOf(id)));
    }

}
