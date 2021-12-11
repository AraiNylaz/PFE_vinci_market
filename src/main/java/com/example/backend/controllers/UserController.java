package com.example.backend.controllers;

import com.example.backend.Config.TokenService;
import com.example.backend.model.User;
import com.example.backend.model.UserDTO;
import com.example.backend.services.User.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Iterable<UserDTO>> allUser(@RequestHeader(name="Authorization")String token){
        if(tokenService.verifyTokenAndAdmin(token)){
            return ResponseEntity.ok().body(userService.findAllUsers());
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody User user){
        UserDTO u=null;
        try{
            user.setCampusName(user.getCampus().getName());
            u= userService.saveUser(user);
            String token=tokenService.createToken(u);
            URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getIdUser()).toUri();
            return ResponseEntity.created(location).header("Authorization",token).build();
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> checkUser(@RequestBody User user)
    {
        UserDTO u=null;
        try{
            u=userService.checkUser(user);
            if(u!=null) {
                String token = tokenService.createToken(u);
                return ResponseEntity.ok().header("Authorization", token).build();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/mail")
    public ResponseEntity<UserDTO> getUserByMail(@RequestHeader(name="Authorization")String token,@RequestParam String mail){
        if(tokenService.verifyTokenAndAdmin(token)){
            UserDTO userDTO=userService.findOneByMail(mail);
            if(userDTO!=null){
                return ResponseEntity.ok().body(userDTO);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<UserDTO> getUserById(@RequestHeader(name="Authorization")String token,@PathVariable String id){
        if(tokenService.verifyTokenAndAdmin(token)){
            UserDTO userDTO=userService.findOneById(new ObjectId(String.valueOf(id)));
            if(userDTO!=null){
                return ResponseEntity.ok().body(userDTO);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/role/{id}")
    public ResponseEntity switchRole(@RequestHeader(name="Authorization")String token,@PathVariable String id){
        if(tokenService.verifyTokenAndAdmin(token)){
            userService.switchRole(new ObjectId(String.valueOf(id)));
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestHeader(name="Authorization")String token,@PathVariable String id,@RequestBody User user){
        if(tokenService.verifyTokenAndAdmin(token)){
            UserDTO userDTO= userService.updateUser(new ObjectId(String.valueOf(id)),user);
            if(userDTO!=null){
                return ResponseEntity.ok().body(userDTO);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity deleteUser(@RequestHeader(name="Authorization")String token,@PathVariable String id){
        if(tokenService.verifyTokenAndAdmin(token)){
            userService.deleteUser(new ObjectId(String.valueOf(id)));
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    @GetMapping("/user/{id}")
    public ResponseEntity switchBan(@RequestHeader(name="Authorization")String token,@PathVariable String id){
        if(tokenService.verifyTokenAndAdmin(token)){
            userService.switchBan(new ObjectId(String.valueOf(id)));
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
