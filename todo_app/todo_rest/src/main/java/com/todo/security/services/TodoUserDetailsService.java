package com.todo.security.services;

import com.todo.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Data
public class TodoUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    private String username;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.todo.domain.UserDetails userDetails = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(userDetails);
    }
}