package com.udemyspringcourse.restfulwebservices.controller;

import com.udemyspringcourse.restfulwebservices.dao.UserDaoService;
import com.udemyspringcourse.restfulwebservices.entity.User;
import com.udemyspringcourse.restfulwebservices.util.Response;
import com.udemyspringcourse.restfulwebservices.util.ResponseUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    /*
    *
    * TODO:
    *  ======Users REST API===========
    *  GET/users
    *  POST/users
    *  GET/users/{id}
    *  DELETE/users/{id}
    *  ======Posts REST API===========
    *  GET/users/{id}/posts
    *  POST/users/{id}/posts
    *  GET/users/{id}/posts/{post_id}
    *  DELETE/users/{id}*/

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class.getName());
    @Autowired
    private UserDaoService userDaoService;

    @GetMapping("/users")
    public Response findAll(){
        List<User> data =  userDaoService.findAll();
        return ResponseUtil.getSuccessResponse(data);
    }

    @GetMapping("/users/{id}")
    public Response findById(@PathVariable Integer id){
        User data =  userDaoService.findById(id);
        return ResponseUtil.getSuccessResponse(data);
    }

    @PostMapping(value = "/users")
    public Object save(@Valid @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseUtil.getErrorResponse(bindingResult.getAllErrors());
        }
        User savedUser = userDaoService.save(user);
        if(savedUser != null){
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedUser.getId()).toUri();
            return ResponseUtil.getSuccessResponse(Map.of("URL",location.toString()));
        }
        return ResponseUtil.getErrorResponse("user not saved");
    }


    @DeleteMapping("/users/{id}")
    public Response deleteById(@PathVariable Integer id){
        if(userDaoService.deleteById(id)){
            return ResponseUtil.getSuccessResponse("user deleted");
        }
        return ResponseUtil.getErrorResponse("user not deleted");
    }

}
