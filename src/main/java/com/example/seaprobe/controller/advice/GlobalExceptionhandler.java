package com.example.seaprobe.controller.advice;

import lombok.extern.log4j.Log4j2;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionhandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity handleBadRequest(Exception e) {
        return ResponseEntity.internalServerError().body("Requested API is not implemented");
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGenericException(Exception e) {
        return ResponseEntity.internalServerError().body("Unable to perform the requested operation. Error: "+ e.getMessage());
    }
}
