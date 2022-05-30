package com.eureka.discovery.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eureka.discovery.model.Credentials;
import com.eureka.discovery.repository.CredentialRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private CredentialRepository credentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(credentialRepository.findAll().parallelStream().noneMatch(p -> p.getUsername().equals(username)))
            throw new UsernameNotFoundException("UserName Not Found");
        Credentials credentials = credentialRepository.findAll().parallelStream().filter(p->p.getUsername().equals(username)).collect(Collectors.toList()).get(0);
        Credentials updateCredentials = new Credentials(credentials.getUsername(), credentials.getPassword(),credentials.getUser_id(),credentials.getRoles());
        return new User(updateCredentials.getUsername(),updateCredentials.getPassword(),updateCredentials.getAuthorities());
    }
}