package com.udemyspringcourse.restfulwebservices.controller;

import com.udemyspringcourse.restfulwebservices.entity.Test;
import com.udemyspringcourse.restfulwebservices.util.Response;
import com.udemyspringcourse.restfulwebservices.util.ResponseUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class.getName());
    @PostMapping("/test")
    public Response validatePattern(@RequestBody @Valid Test test, BindingResult bindingResult){
        LOGGER.info("============ TEST OBJECT ============== {}",test);
        if(bindingResult.hasErrors()){
            return ResponseUtil.getErrorResponse(bindingResult.getAllErrors());
        }
        return ResponseUtil.getSuccessResponse("object is validated");
    }
}
