package com.neothedeveloper.userregistration.service;

import java.util.List;
import java.util.Optional;

import com.neothedeveloper.userregistration.model.User;
import com.neothedeveloper.userregistration.record.RegistrationRequest;

public interface UserService {
    List<User> getUsers();

    User registerUser(RegistrationRequest request);

    Optional<User> findByEmail(String email);
}
