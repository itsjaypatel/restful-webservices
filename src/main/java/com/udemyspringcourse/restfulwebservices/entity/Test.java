package com.udemyspringcourse.restfulwebservices.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Test {


    @DateTimeFormat
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate pattern;


    public Test() {
    }

    public Test(LocalDate pattern) {
        this.pattern = pattern;
    }

    public LocalDate getPattern() {
        return pattern;
    }

    public void setPattern(LocalDate pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return "Test{" +
                "pattern='" + pattern + '\'' +
                '}';
    }
}
