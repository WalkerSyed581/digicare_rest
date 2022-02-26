package com.digicare.digicare_rest_test.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.digicare.digicare_rest_test.exception.PermissionNotFoundException;

@ControllerAdvice
class PermissionNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(PermissionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(PermissionNotFoundException ex) {
        return ex.getMessage();
    }
}
