package com.example.unikomwebresfulapi.controller;

import com.example.unikomwebresfulapi.model.User;
import com.example.unikomwebresfulapi.dto.JwtResponse;
import com.example.unikomwebresfulapi.service.jwt.JwtService;
import com.example.unikomwebresfulapi.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthRestController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login (@RequestBody User user) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.createTokenLogin(authentication);
        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }
}