package com.example.rest.controllers;

import com.example.handler.exception.GenericHandlerException;
import com.example.handler.errorsapi.ApiErrors;
import com.example.handler.exception.OrderNotFoundExcpetion;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler(GenericHandlerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors genericHandlerException(GenericHandlerException ex){
        String msg = ex.getMessage();
        return new ApiErrors(msg);
    }
    @ExceptionHandler(OrderNotFoundExcpetion.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors genericHandlerExceptionNotFound(OrderNotFoundExcpetion ex){
            return new ApiErrors(ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors HandlerMethodNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return new ApiErrors(errors);
    }

}
