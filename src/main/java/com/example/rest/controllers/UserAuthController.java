package com.example.rest.controllers;

import com.example.domain.entity.User;
import com.example.rest.dto.CredentialsTokenDTO;
import com.example.rest.dto.ResponseTokenDTO;
import com.example.rest.dto.SingUpRequest;
import com.example.service.UserAuthService;
import com.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/login")
public class UserAuthController {

    private final UserAuthService service;

    public UserAuthController(UserAuthService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public User signUp(@RequestBody SingUpRequest singUpRequest){
      return   service.signup(singUpRequest);

    }

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    public ResponseTokenDTO getToken(@RequestBody CredentialsTokenDTO dto){
        return service.signin(dto);
    }

}
