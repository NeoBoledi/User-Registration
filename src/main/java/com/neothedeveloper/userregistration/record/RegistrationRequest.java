package com.neothedeveloper.userregistration.record;

public record RegistrationRequest(String firstname,
        String lastname,
        String email,
        String phoneNo,
        String password,
        String role) {

}
