package com.neothedeveloper.userregistration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.neothedeveloper.userregistration.exception.UserAlreadyExistException;
import com.neothedeveloper.userregistration.model.User;
import com.neothedeveloper.userregistration.record.RegistrationRequest;
import com.neothedeveloper.userregistration.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getUsers() {

        return userRepository.findAll();
    }

    @Override
    public User registerUser(RegistrationRequest request) {

        Optional<User> user = this.findByEmail(request.email());
        if (user.isPresent()) {
            throw new UserAlreadyExistException("User with email " + request.email() + "already exists");
        }
        var newUser = new User();
        newUser.setFirstname(request.firstname());
        newUser.setLastname(request.lastname());
        newUser.setEmail(request.email());
        newUser.setPhoneNo(request.phoneNo());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRole(request.role());

        return userRepository.save(newUser);
    }

}
