package com.math012.Picpay.exception;

public class TransactionNotAuthorizedException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public TransactionNotAuthorizedException(String err){
        super(err);
    }
}
