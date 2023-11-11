package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getUsers(){
        return ResponseEntity.ok(new ResponseWrapper("Success",userService.listAllUsers(), HttpStatus.ACCEPTED));
    }

    @GetMapping("/{name}")
    public ResponseEntity<ResponseWrapper> getUsersByUserName(@PathVariable("name")String name){
        return ResponseEntity.ok(new ResponseWrapper("Success",userService.findByUserName(name), HttpStatus.ACCEPTED));

    }
    @PostMapping
    public ResponseEntity<ResponseWrapper> createUser(@RequestBody UserDTO user){
        userService.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper("Success", HttpStatus.CREATED));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateUser(@RequestBody UserDTO user){
        return ResponseEntity.ok(new ResponseWrapper("Success",userService.update(user), HttpStatus.ACCEPTED));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<ResponseWrapper> deleteUser(@PathVariable("username") String user){
        userService.delete(user);
        return ResponseEntity.noContent().build();
    }
}
