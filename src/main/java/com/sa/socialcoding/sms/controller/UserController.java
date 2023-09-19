package com.sa.socialcoding.sms.controller;

import com.sa.socialcoding.sms.dto.UserDTO;
import com.sa.socialcoding.sms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path="/all")
    public List<UserDTO> getAllUsers(
            @RequestParam(name = "userId", required = false) Integer userId,
            @RequestParam(name = "userType", required = false) String userType,
            @RequestParam(name = "firstName", required = false) String firstName ) {
        return userService.getUsers(userId, userType, firstName);
    }

    @PostMapping(path="/submit")
    public ResponseEntity<String> submitUser(
            @RequestHeader(HttpHeaders.ACCEPT) String language,
            @RequestBody UserDTO userRequest){
        String msg = userService.submit(userRequest);
        return ResponseEntity.ok(msg);
    }

    @GetMapping(path="/authenticate/{username}/{password}")
    public Boolean getUserCredential(
            @PathVariable(name = "username") String userName,
            @PathVariable(name = "password") String password ) {
        return userService.validateLogin(userName, password);
    }
}
