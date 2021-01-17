package com.todo.controllers;


import com.todo.modal.JwtResponse;
import com.todo.modal.LoginRequest;
import com.todo.modal.WorkFlowItem;
import com.todo.repository.UserRepository;
import com.todo.security.jwt.JwtUtils;
import com.todo.security.services.UserDetailsImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(value = "login", tags = {"Login Resource"})
@SwaggerDefinition(tags = {
        @Tag(name = "User Resource", description = "Operations pertaining to login")
})
@Slf4j
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/auth/login")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody WorkFlowItem<LoginRequest> workFlowItem) {

        log.info("login attempted");

        LoginRequest loginRequest = workFlowItem.getData();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        log.info("login Successful");

        return ResponseEntity.ok(
                JwtResponse.builder().token(jwt).id(userDetails.getId()).username(userDetails.getUsername()).email(userDetails.getEmail()).roles(roles).build()
        );
    }
}
