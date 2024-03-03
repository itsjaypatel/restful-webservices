package com.udemyspringcourse.restfulwebservices.controller;

import com.udemyspringcourse.restfulwebservices.entity.Comment;
import com.udemyspringcourse.restfulwebservices.entity.Post;
import com.udemyspringcourse.restfulwebservices.entity.User;
import com.udemyspringcourse.restfulwebservices.repository.CommentRepository;
import com.udemyspringcourse.restfulwebservices.repository.PostRepository;
import com.udemyspringcourse.restfulwebservices.repository.UserRepository;
import com.udemyspringcourse.restfulwebservices.util.Response;
import com.udemyspringcourse.restfulwebservices.util.ResponseUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class CommentJPAController {
    /*
     *
     *  TODO:
     *   ======Comment REST API===========
     *   GET/users/{userId}/comments
     *   GET/users/{userId}/posts/{postId}/comments
     *   GET/users/{userId}/posts/{postId}/comments/{commentId}
     *   POST/users/{userId}/posts/{postId}/comments
     *   DELETE/users/{userId}/posts/{postId}/comments/{commentId}*/

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentJPAController.class.getName());

    @GetMapping("/jpa/users/{userId}/comments")
    public Response findAllForUser(@PathVariable Integer userId){
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            return ResponseUtil.getErrorResponse("User does not exists");
        }
        List<Comment> comments = user.getComments();
        comments.forEach(c -> {
            c.setRefs(Map.of("createdBy",userId,"postedOn",c.getPostInfo().getId()));
        });
        return ResponseUtil.getSuccessResponse(comments);
    }

    @GetMapping("/jpa/users/{userId}/posts/{postId}/comments")
    public Response findAllForUserForPost(@PathVariable Integer userId, @PathVariable Integer postId){
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            return ResponseUtil.getErrorResponse("User does not exists");
        }
        Post post = postRepository.findById(postId).orElse(null);
        if(post == null){
            return ResponseUtil.getErrorResponse("Post does not exists");
        }
        List<Comment> comments  = post.getComments().stream().filter(c -> Objects.equals(c.getUserInfo().getId(), userId)).toList();
        comments.forEach(c -> {
            c.setRefs(Map.of("createdBy",userId,"postedOn",postId));
        });
        return ResponseUtil.getSuccessResponse(comments);
    }

    @GetMapping("/jpa/users/{userId}/posts/{postId}/comments/{commentId}")
    public Response findById(@PathVariable Integer userId, @PathVariable Integer postId, @PathVariable Integer commentId){
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            return ResponseUtil.getErrorResponse("User does not exists");
        }
        Post post = postRepository.findById(postId).orElse(null);
        if(post == null){
            return ResponseUtil.getErrorResponse("Post does not exists");
        }
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if(comment == null){
            return ResponseUtil.getErrorResponse("Comment does not exists");
        }
        comment.setRefs(Map.of("createdBy",userId,"postedOn",postId));
        return ResponseUtil.getSuccessResponse(comment);
    }

    @PostMapping("/jpa/users/{userId}/posts/{postId}/comments")
    public Response create(@PathVariable Integer userId, @PathVariable Integer postId, @RequestBody @Valid Comment comment){
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            return ResponseUtil.getErrorResponse("User does not exists");
        }
        Post post = postRepository.findById(postId).orElse(null);
        if(post == null){
            return ResponseUtil.getErrorResponse("Post does not exists");
        }
        LOGGER.info("=============== Comment CREATION =========== {}", comment);
        comment.setUserInfo(user);
        comment.setPostInfo(post);
        commentRepository.save(comment);
        return ResponseUtil.getSuccessResponse("Comment created");
    }

    @DeleteMapping("/jpa/users/{userId}/posts/{postId}/comments/{commentId}")
    public Response delete(@PathVariable Integer userId, @PathVariable Integer postId, @PathVariable Integer commentId){
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            return ResponseUtil.getErrorResponse("User does not exists");
        }
        Post post = postRepository.findById(postId).orElse(null);
        if(post == null){
            return ResponseUtil.getErrorResponse("Post does not exists");
        }
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if(comment == null){
            return ResponseUtil.getErrorResponse("Comment does not exists");
        }
        commentRepository.deleteById(commentId);
        return ResponseUtil.getSuccessResponse("Comment deleted");
    }
}
