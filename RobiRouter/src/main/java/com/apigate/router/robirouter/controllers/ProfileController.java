package com.apigate.router.robirouter.controllers;

import com.apigate.router.robirouter.model.Profile;
import com.apigate.router.robirouter.services.api.token.ProfileService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/profileManager")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    private static final Logger logger = LoggerFactory.getLogger(RobiRouter.class);

    @RequestMapping(path = "add")

    public ResponseEntity saveProfile(@RequestBody Profile profile) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        UUID correlationID = UUID.randomUUID();
        logger.info("Received profile to correlation id {}  {} ", profile);

        Profile savedProfile = profileService.create(profile);
        return new ResponseEntity<>(new Gson().toJson(savedProfile),headers, HttpStatus.CREATED);
    }

}
