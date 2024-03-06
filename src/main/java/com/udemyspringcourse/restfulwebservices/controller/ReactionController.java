package com.udemyspringcourse.restfulwebservices.controller;

import com.udemyspringcourse.restfulwebservices.entity.EMOJI;
import com.udemyspringcourse.restfulwebservices.entity.Post;
import com.udemyspringcourse.restfulwebservices.entity.Reaction;
import com.udemyspringcourse.restfulwebservices.entity.User;
import com.udemyspringcourse.restfulwebservices.repository.PostRepository;
import com.udemyspringcourse.restfulwebservices.repository.ReactionRepository;
import com.udemyspringcourse.restfulwebservices.repository.UserRepository;
import com.udemyspringcourse.restfulwebservices.util.Response;
import com.udemyspringcourse.restfulwebservices.util.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReactionController {

    @Autowired
    public ReactionRepository reactionRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PostRepository postRepository;


    @PostMapping("/users/{userId}/posts/{postId}/reaction/{reaction}")
    public Response reactPost(@Valid @PathVariable Integer userId,@Valid @PathVariable Integer postId,
                              @Valid @PathVariable String reaction){

        EMOJI emoji = EMOJI.getEmoji(reaction);
        if(emoji == null){
            return ResponseUtil.getErrorResponse(reaction + " emoji doesn't exists");
        }

        User userInfo = userRepository.findById(userId).orElse(null);
        Post postInfo = postRepository.findById(postId).orElse(null);

        if(userInfo == null){
            return ResponseUtil.getErrorResponse("user doesn't exists");
        }
        if(postInfo == null){
            return ResponseUtil.getErrorResponse("post doesn't exists");
        }

        List<Reaction> reactions = reactionRepository.findByUserIdAndPostId(userId,postId);
        System.out.println(reactions);
        if(reactions.isEmpty()){
            Reaction currentReaction = new Reaction(emoji,userId,postId);
            reactionRepository.save(currentReaction);
            return ResponseUtil.getSuccessResponse("User " + userId + " reacted with " + emoji);
        }else{
            Reaction previousReaction = reactions.get(0);
            reactionRepository.delete(previousReaction);
            Reaction currentReaction = new Reaction(emoji,userId,postId);
            reactionRepository.save(currentReaction);

            return ResponseUtil.getSuccessResponse("User " + userId + " reacted with " + emoji);
        }
    }
}
