package com.ilenkov.bank.identityservice.service;


import com.ilenkov.bank.identityservice.entity.UserCredential;
import com.ilenkov.bank.identityservice.entity.dto.AuthRequest;
import com.ilenkov.bank.identityservice.entity.dto.UserInfoRequest;
import com.ilenkov.bank.identityservice.exceptions.AuthFailedException;
import com.ilenkov.bank.identityservice.exceptions.UserAlreadyExistsException;
import com.ilenkov.bank.identityservice.feighinterface.UserInfoInterface;
import com.ilenkov.bank.identityservice.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserInfoInterface userInfoInterface;

    @Autowired
    public AuthServiceImpl(UserCredentialRepository userCredentialRepository,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService,
                           AuthenticationManager authenticationManager,
                           UserInfoInterface userInfoInterface) {
        this.userCredentialRepository = userCredentialRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userInfoInterface = userInfoInterface;
    }

    @Override
    public void saveUser(UserCredential userCredential) throws UserAlreadyExistsException {
        Optional<UserCredential> userCredentialOptional =
                userCredentialRepository.findByUsername(userCredential.getUsername());
        if (userCredentialOptional.isEmpty()) {
            userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
            userCredentialRepository.save(userCredential);


            String token = "Bearer " + jwtService.generateToken(userCredential.getUsername());
            userInfoInterface.createUser(token, new UserInfoRequest(userCredential.getEmail(),
                    userCredential.getName(),
                    userCredential.getId()));
//            userCredentialOptional = userCredentialRepository.findByUsername(userCredential.getUsername());
//            Long id = userCredentialOptional.get().getId();

        } else {
            throw new UserAlreadyExistsException("User with username = " + userCredential.getUsername() + " already exists");
        }
    }

    @Override
    public String generateToken(AuthRequest authRequest) throws AuthFailedException {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                        authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new AuthFailedException("Authentication failed");
        }
    }

    @Override
    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
