package com.math012.Picpay.controller;

import com.math012.Picpay.dtos.TransferDTO;
import com.math012.Picpay.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/transfer/v1")
public class TransferController {

    @Autowired
    private TransferService service;

    @PostMapping(value = "/transfer")
    public ResponseEntity<TransferDTO> sendTransfer(@RequestBody TransferDTO dto){
        return ResponseEntity.ok().body(service.makeTransfer(dto));
    }
}
