package com.sorsix.finkicommunity.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/forum/users")
@CrossOrigin(origins = "http://localhost:4200")  // Enabling Cross Origin Requests for a RESTful Web Service

public class PostController {

}
