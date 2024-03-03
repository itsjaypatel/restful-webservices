package com.udemyspringcourse.restfulwebservices.util;

public class Response<E> {

    private String status;
    private E data;
    private int statusCode;

    public Response(){
    }
    public Response(String status,int statusCode,E data){
        this.data = data;
        this.status = status;
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", statusCode=" + statusCode +
                '}';
    }

}
