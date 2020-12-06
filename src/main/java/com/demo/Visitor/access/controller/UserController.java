package com.demo.Visitor.access.controller;

import com.demo.Visitor.access.dto.RegisterUserDto;
import com.demo.Visitor.access.dto.ResponseDto;
import com.demo.Visitor.access.exception.LoginException;
import com.demo.Visitor.access.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseDto register(@Valid @RequestBody RegisterUserDto registerUserDto) throws LoginException {
        boolean register = userService.register(registerUserDto);
        if (register)
            return new ResponseDto("Registration Successful", 200);
        throw new LoginException("Employee Id Exist", 400);
    }

}
