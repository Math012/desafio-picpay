package com.math012.Picpay.controller;

import com.math012.Picpay.dtos.UserDTO;
import com.math012.Picpay.model.UserModel;
import com.math012.Picpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/register/v1")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping(value = "/user")
    public ResponseEntity<UserModel> registerUser(@RequestBody UserDTO user){
        return ResponseEntity.ok().body(service.createUser(user));
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }
}
