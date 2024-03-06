package com.udemyspringcourse.restfulwebservices.entity;

public enum REACTION {

    LIKE("LIKE"),
    CELEBRATE("CELEBRATE"),
    LOVE("LOVE");
    

    private String code;

    private REACTION(String code){
        this.code = code;
    }
}
