package com.math012.externalservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class VerifyTransferService {

    @Autowired
    private RestTemplate restTemplate;


    public boolean verifyTransfer(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> authorizedService = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);
        String message = authorizedService.getBody().get("message").toString();
        if (message.equalsIgnoreCase("Autorizado")){
            return true;
        }else return false;
    }
}
