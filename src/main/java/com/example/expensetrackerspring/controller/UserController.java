package com.example.expensetrackerspring.controller;

import com.example.expensetrackerspring.dto.LoginRequest;
import com.example.expensetrackerspring.dto.LoginResponse;
import com.example.expensetrackerspring.dto.SignupRequest;
import com.example.expensetrackerspring.helper.JwtHelper;
import com.example.expensetrackerspring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class UserController {
    @Autowired
    UserService userService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignupRequest signupRequest) {
        userService.signup(signupRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String token = JwtHelper.generateToken(request.getEmail()) ; // TODO : implement
        LoginResponse loginResponse = new LoginResponse(request.getEmail(),token);
        return ResponseEntity.ok(loginResponse);
    }

}
