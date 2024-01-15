package com.example.rest.dto;

import com.example.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingUpRequest {
    private String email;
    private String password;
    private Role role;
}
