package com.demo.Visitor.access.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginException extends RuntimeException {

    public String message;
    public int statusCode;
 public LoginException(String message,int statusCode){
	
}

}
