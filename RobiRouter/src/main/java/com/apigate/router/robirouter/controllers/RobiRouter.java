package com.apigate.router.robirouter.controllers;

import com.apigate.router.robirouter.services.api.token.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/smsmessaging")
public class RobiRouter {

    @Autowired
    private ProfileService profileService;

    private static final Logger logger = LoggerFactory.getLogger(RobiRouter.class);

    @RequestMapping(path = "test")
    public ResponseEntity routeRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        UUID correlationID = UUID.randomUUID();
        logger.info("Received SMS DN body to correlation id {}  {} ", correlationID);
        return new ResponseEntity<>("{}",headers, HttpStatus.OK);
    }






}
