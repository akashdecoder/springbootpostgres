package com.example.springbootpostgres.service;

import com.example.springbootpostgres.model.User;
import com.example.springbootpostgres.repository.UserRepository;
import com.example.springbootpostgres.security.UserRegistrationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserRegistrationDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not Found");
        }
        return new UserRegistrationDetails(user);
    }
}