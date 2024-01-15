package com.example.service;

import com.example.domain.entity.User;
import com.example.rest.dto.CredentialsTokenDTO;
import com.example.rest.dto.ResponseTokenDTO;
import com.example.rest.dto.SingUpRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();


}
