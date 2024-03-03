package com.udemyspringcourse.restfulwebservices.controller;

import com.udemyspringcourse.restfulwebservices.dao.UserDaoService;
import com.udemyspringcourse.restfulwebservices.entity.User;
import com.udemyspringcourse.restfulwebservices.repository.UserRepository;
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
public class UserJPAController {

    /*
    *
    *  TODO:
    *   ======Users REST API===========
    *   GET/users
    *   POST/users
    *   GET/users/{id}
    *   DELETE/users/{id}*/

    private static Logger LOGGER = LoggerFactory.getLogger(UserJPAController.class.getName());
    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/jpa/users")
    public Response findAll(){
        List<User> data =  userRepository.findAll();
        return ResponseUtil.getSuccessResponse(data);
    }

    @GetMapping("/jpa/users/{id}")
    public Response findById(@PathVariable Integer id){
        User data =  userRepository.findById(id).orElse(null);
        return data != null ? ResponseUtil.getSuccessResponse(data) : ResponseUtil.getErrorResponse("User not found");
    }

    @PostMapping(value = "/jpa/users")
    public Object save(@Valid @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseUtil.getErrorResponse(bindingResult.getAllErrors());
        }
        User savedUser = userRepository.save(user);
        if(savedUser != null){
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedUser.getId()).toUri();
            return ResponseUtil.getSuccessResponse(Map.of("URL",location.toString()));
        }
        return ResponseUtil.getErrorResponse("user not saved");
    }


    @DeleteMapping("/jpa/users/{id}")
    public Response deleteUser(@PathVariable Integer id){
        try{
            userRepository.deleteById(id);
            return ResponseUtil.getSuccessResponse("user deleted");
        }catch (Exception e){
            return ResponseUtil.getErrorResponse("user not deleted");
        }
    }

}
