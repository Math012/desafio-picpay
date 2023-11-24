package com.math012.Picpay.config;

import com.math012.externalservice.VerifyTransferService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public VerifyTransferService verifyTransferService(){
        return new VerifyTransferService();
    }


}
