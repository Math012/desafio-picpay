package com.math012.Picpay.exception;

public class ValueEnoughException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ValueEnoughException(String err){
        super(err);
    }
}
