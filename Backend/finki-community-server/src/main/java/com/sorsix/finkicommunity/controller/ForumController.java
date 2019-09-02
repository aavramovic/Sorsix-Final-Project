package com.sorsix.finkicommunity.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/forum")
@CrossOrigin  // Enabling Cross Origin Requests for a RESTful Web Service
public class ForumController {

    @GetMapping
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome to the forum FINKI-COMMUNITY");
    }
}
