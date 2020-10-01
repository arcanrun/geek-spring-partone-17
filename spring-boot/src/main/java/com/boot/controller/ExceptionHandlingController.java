package com.boot.controller;

import com.boot.controller.exceptions.ResourceNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler
    public ModelAndView exceptionHandler(ResourceNotFoundException resourceNotFoundException){
        ModelAndView modelAndView = new ModelAndView("404");
        modelAndView.getModel().put("error_message", "404");
        return modelAndView;
    }
}
