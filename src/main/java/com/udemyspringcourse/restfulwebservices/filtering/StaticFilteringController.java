package com.udemyspringcourse.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StaticFilteringController {

    //applicable on all api which returns SomeBean as response
    record SomeBean(@JsonIgnore String field1, @JsonIgnore String field2, String field3){}

    @GetMapping("/static-filtering")
    public SomeBean doFiltering(){
            return new SomeBean("value1","value2","value3");
    }

    @GetMapping("/static-filtering-list")
    public List<SomeBean> doFilteringList(){
        return List.of(new SomeBean("value1","value2","value3"),new SomeBean("value4","value5","value6")) ;
    }
}
