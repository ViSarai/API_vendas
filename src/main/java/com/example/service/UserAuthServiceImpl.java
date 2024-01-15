package com.example.service;

import com.example.domain.entity.User;
import com.example.domain.repo.UserRepository;
import com.example.rest.dto.CredentialsTokenDTO;
import com.example.rest.dto.ResponseTokenDTO;
import com.example.rest.dto.SingUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {
    private final UserRepository repo;
    private final PasswordEncoder password;
    private final AuthenticationManager manager;
    private final JWTService service;

    public User signup( SingUpRequest request){

        User user = new User();

        user.setEmail(request.getEmail());
        user.setPassword(password.encode(request.getPassword()));
        user.setRole(request.getRole());

        return repo.save(user);

    }

    public ResponseTokenDTO signin(CredentialsTokenDTO dto){
        manager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(),
        dto.getPassword()));

        var user = repo.findByEmail(dto.getEmail())
                .orElseThrow(()-> new IllegalArgumentException("Invalid email or password."));
        var jwt = service.generateToken(user);

        ResponseTokenDTO token = new ResponseTokenDTO();

        token.setToken(jwt);

        return token;
    }
}
