package com.math012.Picpay.exception;

public class UserLoginException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UserLoginException(String err){
        super(err);
    }
}
