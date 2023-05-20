package com.javaexample.springmongodb.controller.advice;

import com.javaexample.springmongodb.dto.ErrorDto;
import com.javaexample.springmongodb.exception.SpringMongodbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Properties;

@ControllerAdvice
public class ErrorHandlerAdviceController {

    @Autowired
    private Properties errorProperties;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleApiException(final Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new ErrorDto("10001", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SpringMongodbException.class)
    public ResponseEntity<ErrorDto> handleProductDataBaseException(final SpringMongodbException ex) {
        return new ResponseEntity<>(new ErrorDto(errorProperties.getProperty(ex.getType().getCode()),
                errorProperties.getProperty(ex.getType().getDescription())), HttpStatus.BAD_REQUEST);
    }
}
