package com.example.service;

import com.example.domain.entity.User;
import com.example.domain.enums.Role;
import com.example.domain.repo.UserRepository;
import com.example.rest.dto.CredentialsTokenDTO;
import com.example.rest.dto.ResponseTokenDTO;
import com.example.rest.dto.SingUpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service

public class UserServiceImpl implements UserService  {

    private final UserRepository repo;




    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;

    }
    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return repo.findByEmail(username)
                                .orElseThrow(()-> new UsernameNotFoundException("user not found. "));
            }
        };
    }



}