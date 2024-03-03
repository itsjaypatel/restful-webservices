package com.udemyspringcourse.restfulwebservices.util;

public enum ResponseType {

    SUCCESS("success",200),
    ERROR("error",401);

    private String status;
    private int code;

    private ResponseType(String status,int code){
        this.status = status;
        this.code = code;
    }
}
