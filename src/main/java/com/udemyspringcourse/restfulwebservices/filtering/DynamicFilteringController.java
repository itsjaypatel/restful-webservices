package com.udemyspringcourse.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DynamicFilteringController {

    @JsonFilter("DynamicFilterBeanFilter")
    record DynamicFilterBean(String field1,String field2, String field3){}

    @GetMapping("/dynamic-filtering")
    public MappingJacksonValue doFiltering(){
        DynamicFilterBean dynamicFilterBean = new DynamicFilterBean("value1", "value2", "value3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dynamicFilterBean);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
        FilterProvider filters= new SimpleFilterProvider().addFilter("DynamicFilterBeanFilter",filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping("/dynamic-filtering-list")
    public List<DynamicFilterBean> doFilteringList(){
        return List.of(new DynamicFilterBean("value1","value2","value3"),new DynamicFilterBean("value4","value5","value6")) ;
    }
}
