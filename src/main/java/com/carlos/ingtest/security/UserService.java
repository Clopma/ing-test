package com.carlos.ingtest.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Retrieves a mock user
        if (username.equals("user")){
            return new User("user", "password"); //TODO: create user entity etc
        }
        return null; // TODO: Throw exception, handle etc

    }

}