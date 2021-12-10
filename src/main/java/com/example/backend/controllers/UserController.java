package com.example.backend.controllers;

import com.example.backend.model.User;
import com.example.backend.services.User.UserService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
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

    @PostMapping
    public User addUser (@RequestBody User user) {
        User u=null;
        try{
            u= userService.saveUser(user);
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return u;
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
