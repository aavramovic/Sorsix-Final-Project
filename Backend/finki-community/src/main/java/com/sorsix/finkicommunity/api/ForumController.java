package com.sorsix.finkicommunity.api;

import com.sorsix.finkicommunity.models.classes.Post;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/forum")
public class ForumController {


    @GetMapping("/main")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Post> getTop10Posts(){
        return null;
    }
}
