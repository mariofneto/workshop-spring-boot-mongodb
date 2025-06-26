package com.nelioalves.workshopmongo.resources;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDto;
import com.nelioalves.workshopmongo.resources.util.URL;
import com.nelioalves.workshopmongo.service.PostService;
import com.nelioalves.workshopmongo.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post post = postService.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping("/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@PathParam("text") String text){
        text = URL.decodeParam(text);
        List<Post> list = postService.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }
}
