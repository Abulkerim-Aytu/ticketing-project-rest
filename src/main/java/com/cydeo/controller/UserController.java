package com.cydeo.controller;

import com.cydeo.annotation.ExecutionTime;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.UserDTO;
import com.cydeo.exception.TicketingProjectException;
import com.cydeo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "UserController",description = "User API")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ExecutionTime
    @GetMapping
    @RolesAllowed({"Manager","Admin"})
    @Operation(summary = "Get Users")
    public ResponseEntity<ResponseWrapper> getUsers(){
        return ResponseEntity.ok(new ResponseWrapper("Users are successfully retrieved",userService.listAllUsers(), HttpStatus.OK));
    }

    @ExecutionTime
    @GetMapping("/{name}")
    @RolesAllowed({"Admin"})
    @Operation(summary = "Get User by username")
    public ResponseEntity<ResponseWrapper> getUsersByUserName(@PathVariable("name")String name){
        return ResponseEntity.ok(new ResponseWrapper("User is successfully retrieved",userService.findByUserName(name), HttpStatus.OK));

    }
    @PostMapping
    @RolesAllowed({"Admin"})
    @Operation(summary = "Create User")
    public ResponseEntity<ResponseWrapper> createUser(@RequestBody UserDTO user){
        userService.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper("User is successfully created", HttpStatus.CREATED));
    }

    @PutMapping
    @RolesAllowed({"Admin"})
    @Operation(summary = "Update User")
    public ResponseEntity<ResponseWrapper> updateUser(@RequestBody UserDTO user){
        return ResponseEntity.ok(new ResponseWrapper("User is successfully updated",userService.update(user), HttpStatus.OK));
    }

    @DeleteMapping("/{username}")
    @RolesAllowed({"Admin"})
    @Operation(summary = "Delete User")
    public ResponseEntity<ResponseWrapper> deleteUser(@PathVariable("User is successfully deleted") String user) throws TicketingProjectException {
        userService.delete(user);
        return ResponseEntity.noContent().build();
    }
}
