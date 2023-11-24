package com.math012.Picpay.controller;

import com.math012.Picpay.dtos.UserTypeDTO;
import com.math012.Picpay.model.UserTypeModel;
import com.math012.Picpay.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/roles")
public class UserTypeController {

    @Autowired
    private UserTypeService service;

    @GetMapping
    public ResponseEntity<List<UserTypeModel>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping(value = "/createrole")
    public ResponseEntity<UserTypeModel> createType(@RequestBody UserTypeDTO userTypeDTO){
        return ResponseEntity.ok(service.createUserType(userTypeDTO.role()));
    }
}
