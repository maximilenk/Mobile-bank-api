package com.ilenkov.bank.identityservice.config;

import com.ilenkov.bank.identityservice.entity.UserCredential;
import com.ilenkov.bank.identityservice.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> userCredential = userCredentialRepository.findByUsername(username);
        return userCredential.map(UserDetailsImpl::new)
                .orElseThrow(()-> new UsernameNotFoundException("user " + username + " not found"));
    }
}
