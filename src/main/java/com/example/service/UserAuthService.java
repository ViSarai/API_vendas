package com.example.service;

import com.example.domain.entity.User;
import com.example.rest.dto.CredentialsTokenDTO;
import com.example.rest.dto.ResponseTokenDTO;
import com.example.rest.dto.SingUpRequest;

public interface UserAuthService {

    User signup(SingUpRequest request);

    ResponseTokenDTO signin(CredentialsTokenDTO dto);
}
