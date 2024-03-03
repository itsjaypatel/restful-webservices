package com.udemyspringcourse.restfulwebservices.controller;

import com.udemyspringcourse.restfulwebservices.entity.Post;
import com.udemyspringcourse.restfulwebservices.entity.User;
import com.udemyspringcourse.restfulwebservices.repository.PostRepository;
import com.udemyspringcourse.restfulwebservices.repository.UserRepository;
import com.udemyspringcourse.restfulwebservices.util.Response;
import com.udemyspringcourse.restfulwebservices.util.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class PostJPAController {

    /*
     *
     * TODO:
     *  ======Posts REST API===========
     *  GET/users/{id}/posts
     *  POST/users/{id}/posts
     *  GET/users/{id}/posts/{post_id}
     *  DELETE/users/{id}*/


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/jpa/users/{id}/posts")
    public Response findPosts(@PathVariable(name = "id") Integer userId){
        User user = userRepository.findById(userId).orElse(null);

        if(user == null){
            return ResponseUtil.getErrorResponse("User does not exists");
        }
        List<Post> posts = user.getPosts();
        posts.forEach(p -> p.setRefs(Map.of("createdBy",userId)));
        return ResponseUtil.getSuccessResponse(posts);
    }

    @GetMapping("/jpa/users/{userId}/posts/{postId}")
    public Response findPost(@PathVariable Integer userId,@PathVariable Integer postId){
        User user = userRepository.findById(userId).orElse(null);

        if(user == null){
            return ResponseUtil.getErrorResponse("User does not exists");
        }

        Post post = postRepository.findById(postId).orElse(null);

        if(post == null){
            return ResponseUtil.getErrorResponse("Post does not exists");
        }
        post.setRefs(Map.of("createdBy",userId));
        return ResponseUtil.getSuccessResponse(post);
    }

    @PostMapping("/jpa/users/{id}/posts")
    public Response createPost(@PathVariable(name = "id") Integer userId, @Valid @RequestBody Post post){
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            return ResponseUtil.getErrorResponse("User does not exists");
        }
        post.setUserInfo(user);
        postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{postId}")
                .buildAndExpand(post.getId()).toUri();
        return ResponseUtil.getSuccessResponse(Map.of("URL",location.toString()));
    }


    @DeleteMapping("/jpa/users/{userId}/posts/{postId}")
    public Response deletePost(@PathVariable Integer userId,@PathVariable Integer postId){
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            return ResponseUtil.getErrorResponse("User does not exists");
        }
        postRepository.deleteById(postId);
        return ResponseUtil.getSuccessResponse("Post deleted");
    }
}
