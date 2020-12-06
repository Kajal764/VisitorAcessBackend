package com.demo.Visitor.access.exception;

import com.demo.Visitor.access.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = LoginException.class)
    public ResponseEntity<Object> loginException(LoginException loginException) {
        return new ResponseEntity<>(new ResponseDto(loginException.message, 400), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new ResponseDto(e.getBindingResult().getFieldError().getDefaultMessage(), 400), HttpStatus.BAD_REQUEST);
    }
}
