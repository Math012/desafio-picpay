package com.math012.Picpay.exception;

public class AccountNotAuthorizedException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public AccountNotAuthorizedException(String err){
        super(err);
    }
}
