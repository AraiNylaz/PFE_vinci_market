package com.example.backend.controllers;

import com.example.backend.Enums.Campus;
import com.example.backend.model.User;
import com.example.backend.model.UserDTO;
import com.example.backend.services.User.UserService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Iterable<UserDTO> allUser(){
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
    public UserDTO addUser (@RequestBody User user) {
        if(!user.getMail().endsWith("@student.vinci.be") || !user.getMail().endsWith("@vinci.be")){
            return null;
        }

        UserDTO u=null;
        try{
            u= userService.saveUser(user);
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return u;
    }

    @PostMapping("/login")
    public UserDTO checkUser(@RequestBody User user){
        return userService.checkUser(user);
    }

    @GetMapping("/mail")
    public UserDTO getUserByMail(@RequestParam String mail){
        return userService.findOneByMail(mail);
    }

    @GetMapping("/detail/{id}")
    public UserDTO getUserById(@PathVariable String id){
        return userService.findOneById(new ObjectId(String.valueOf(id)));
    }

    @GetMapping("/role/{id}")
    public void switchRole(@PathVariable String id){
        userService.switchRole(new ObjectId(String.valueOf(id)));
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable String id,@RequestBody User user){
        System.out.println("user : " + user);
        System.out.println("id : " + id);
        System.out.println("objectid   : "+ new ObjectId(String.valueOf(id)));
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
