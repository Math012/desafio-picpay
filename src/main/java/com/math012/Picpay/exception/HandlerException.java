package com.math012.Picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class HandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountNotAuthorizedException.class)
    public ResponseEntity<ModelException> handlerAccountNotAuthorizedException(Exception e, WebRequest request){
        var exception = new ModelException(new Date(),e.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(exception, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ValueEnoughException.class)
    public ResponseEntity<ModelException> handlerValueEnoughException(Exception e, WebRequest request){
        var exception = new ModelException(new Date(),e.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionNotAuthorizedException.class)
    public ResponseEntity<ModelException> handlerTransactionNotAuthorizedException(Exception e, WebRequest request) {
        var exception = new ModelException(new Date(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserLoginException.class)
    public ResponseEntity<ModelException> handlerUserLoginException(Exception e, WebRequest request) {
        var exception = new ModelException(new Date(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exception, HttpStatus.CONFLICT);
    }
}
