package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @RolesAllowed({"Manager","Admin"})
    public ResponseEntity<ResponseWrapper> getUsers(){
        return ResponseEntity.ok(new ResponseWrapper("Users are successfully retrieved",userService.listAllUsers(), HttpStatus.OK));
    }

    @GetMapping("/{name}")
    @RolesAllowed({"Admin"})
    public ResponseEntity<ResponseWrapper> getUsersByUserName(@PathVariable("name")String name){
        return ResponseEntity.ok(new ResponseWrapper("User is successfully retrieved",userService.findByUserName(name), HttpStatus.OK));

    }
    @PostMapping
    @RolesAllowed({"Admin"})
    public ResponseEntity<ResponseWrapper> createUser(@RequestBody UserDTO user){
        userService.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper("User is successfully created", HttpStatus.CREATED));
    }

    @PutMapping
    @RolesAllowed({"Admin"})
    public ResponseEntity<ResponseWrapper> updateUser(@RequestBody UserDTO user){
        return ResponseEntity.ok(new ResponseWrapper("User is successfully updated",userService.update(user), HttpStatus.OK));
    }

    @DeleteMapping("/{username}")
    @RolesAllowed({"Admin"})
    public ResponseEntity<ResponseWrapper> deleteUser(@PathVariable("User is successfully deleted") String user){
        userService.delete(user);
        return ResponseEntity.noContent().build();
    }
}
