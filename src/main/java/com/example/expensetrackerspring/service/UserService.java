package com.example.expensetrackerspring.service;

import com.example.expensetrackerspring.dto.SignupRequest;
import com.example.expensetrackerspring.exception.UserAlreadyExistsException;
import com.example.expensetrackerspring.model.User;
import com.example.expensetrackerspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
//@Transactional(readOnly=true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @Transactional
    public void signup(SignupRequest signupRequest) {
        Optional<User> existingUser = userRepository.findByEmail(signupRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User exist with the email. Please Login!"); // TODO: change exception
        }
        User user = new User(signupRequest.getName(), signupRequest.getEmail(), passwordEncoder.encode(signupRequest.getPassword()));
        userRepository.save(user);
    }


}
