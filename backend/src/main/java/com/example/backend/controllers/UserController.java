package com.example.backend.controllers;

import com.example.backend.model.User;
import com.example.backend.services.User.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Iterable<User> allUser(){
        return userService.findAllUsers();
    }

    @PostMapping
    public ResponseEntity<Object> addUser (@RequestBody User user){
        User u = userService.saveUser(user) ;
        if(u==null) return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(u.getIdUser())
                .toUri() ;
        return  ResponseEntity.created(location).build();
    }

    @PostMapping("/login")
    public boolean checkUser(@RequestBody User user){
        return userService.checkUser(user);
    }

    @GetMapping("/mail")
    public User getUserByMail(@RequestParam String mail){
        return userService.findOneByMail(mail);
    }

    @GetMapping("/detail/{id}")
    public User getUserById(@PathVariable int id){
        return userService.findOneById(new ObjectId(String.valueOf(id)));
    }

    @PostMapping("/role/{id}")
    public void switchRole(@PathVariable int id){
        userService.switchRole(new ObjectId(String.valueOf(id)));
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user){
       return userService.updateUser(user);
    }

    @GetMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(new ObjectId(String.valueOf(id)));
    }
}
