package com.udemyspringcourse.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    record Name(String firstName,String secondName){}
    record PersonV1(String name){}
    record PersonV2(Name name){}

    @GetMapping("/v1/person")
    public PersonV1 personURLVersion1(){
        return new PersonV1("Jay Patel");
    }

    @GetMapping("/v2/person")
    public PersonV2 personURLVersion2(){
        return new PersonV2(new Name("Jay","Patel"));
    }


    @GetMapping(value = "/person",params = "version=1")
    public PersonV1 personRequestParamVersion1(){
        return new PersonV1("Jay Patel");
    }

    @GetMapping(value= "/person" ,params = "version=2")
    public PersonV2 personRequestParamVersion2(){
        return new PersonV2(new Name("Jay","Patel"));
    }


    @GetMapping(value = "/person",headers = "X-API-VERSION=1")
    public PersonV1 personRequestHeadersVersion1(){
        return new PersonV1("Jay Patel");
    }

    @GetMapping(value= "/person" ,headers = "X-API-VERSION=2")
    public PersonV2 personRequestHeadersVersion2(){
        return new PersonV2(new Name("Jay","Patel"));
    }

    @GetMapping(value = "/person",produces = "application/vnd.app-v1+json")
    public PersonV1 personAcceptHeadersVersion1(){
        return new PersonV1("Jay Patel");
    }

    @GetMapping(value= "/person" ,produces = "application/vnd.app-v2+json")
    public PersonV2 personAcceptHeadersVersion2(){
        return new PersonV2(new Name("Jay","Patel"));
    }
}
