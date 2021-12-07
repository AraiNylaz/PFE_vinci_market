package com.example.backend.controllers;

import com.example.backend.model.User;
import com.example.backend.services.UserService;
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

    @GetMapping
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
}
