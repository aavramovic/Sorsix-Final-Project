package com.sorsix.finkicommunity.services;

import com.sorsix.finkicommunity.models.classes.Post;
import com.sorsix.finkicommunity.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public List<Post> getLast10Posts() {
        return
    }
}
