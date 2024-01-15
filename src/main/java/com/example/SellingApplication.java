package com.example;

import com.example.domain.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SellingApplication   {

    @Autowired
    private ClientRepository clientRepository;
    public static void main(String[] args) {
        SpringApplication.run(SellingApplication.class,args);
    }

}
