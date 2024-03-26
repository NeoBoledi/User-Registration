package com.neothedeveloper.userregistration.event.listener;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.neothedeveloper.userregistration.event.RegistrationCompleteEvent;
import com.neothedeveloper.userregistration.model.User;

@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private static final Logger log = LoggerFactory.getLogger(RegistrationCompleteEventListener.class);

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {

        // Getting the newly registered user
        User theUser = event.getUser();

        // Create a verificationn token for the user
        String verificationToken = UUID.randomUUID().toString();

        // save the token for user

        // Verification url to be sent to the user
        String url = event.getApplicationUrl() + "/register/verifyEmail?token =" + verificationToken;

        // Send the email

        log.info("Click the link to verify your registration : {}", url);
    }

}
