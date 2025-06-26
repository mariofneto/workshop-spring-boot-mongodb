package com.nelioalves.workshopmongo.service;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDto;
import com.nelioalves.workshopmongo.repository.PostRepository;
import com.nelioalves.workshopmongo.repository.UserRepository;
import com.nelioalves.workshopmongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id){
        Post post =  postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não " +
                "encontrado"));
        return post;
    }

    public List<Post> findByTitle(String text){
        return postRepository.findByTitleContainingIgnoreCase(text);
    }

}
