package com.neothedeveloper.userregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neothedeveloper.userregistration.event.RegistrationCompleteEvent;
import com.neothedeveloper.userregistration.model.User;
import com.neothedeveloper.userregistration.record.RegistrationRequest;
import com.neothedeveloper.userregistration.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private final UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    public RegistrationController(UserService userService, ApplicationEventPublisher publisher) {
        this.userService = userService;
        this.publisher = publisher;
    }

    @PostMapping("/register")
    public String registerUser(RegistrationRequest registrationRequest, final HttpServletRequest request) {

        User user = userService.registerUser(registrationRequest);

        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "Successfully Registered! Check email to complete the process." + user;
    }

    public String applicationUrl(HttpServletRequest request) {

        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}
