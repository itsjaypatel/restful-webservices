package com.udemyspringcourse.restfulwebservices.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class DateFormatValidator implements ConstraintValidator<CustomDateFormatValidation,LocalDate> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateFormatValidator.class.getName());


    @Override
    public boolean isValid(LocalDate pattern, ConstraintValidatorContext context) {
        try{

            LOGGER.info("date validation started.......... {}" ,pattern);
            if(pattern == null){
                LOGGER.info("date validation failed..........");
                return false;
            }
            LOGGER.info("date validation completed..........");
            return true;
        }catch (DateTimeException e){
            LOGGER.info("date validation failed..........");
            return false;
        }
    }
}
