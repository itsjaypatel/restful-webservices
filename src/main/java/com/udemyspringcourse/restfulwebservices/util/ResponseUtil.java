package com.udemyspringcourse.restfulwebservices.util;


public class ResponseUtil {


    public static Response<Object> getSuccessResponse(Object data){
        return new Response<>("success",200,data);
    }

    public static Response<Object> getErrorResponse(Object message){
        return new Response<>("error",404,message);
    }
}
