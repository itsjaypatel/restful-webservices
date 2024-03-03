package com.udemyspringcourse.restfulwebservices.controller;

import com.udemyspringcourse.restfulwebservices.util.Response;
import com.udemyspringcourse.restfulwebservices.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public Response helloWorldBean(){
        return ResponseUtil.getSuccessResponse("Hello World Bean");
    }

    @GetMapping("/good-morning")
    public String wishGoodMoring(){
        Locale locale = LocaleContextHolder.getLocale();
        System.out.println(locale);
        return messageSource.getMessage("good.morning.message",null,"Default Message", locale);
    }

    @GetMapping("/hello-world/path-variable/{name}")
    public Response helloWorldPathVariable(@PathVariable String name){
        return ResponseUtil.getSuccessResponse("Hello World " + name);
    }

    @GetMapping("/hello-world-bean-error")
    public Response errorHandler(){
        return ResponseUtil.getErrorResponse("404 resource not found");
    }
}
