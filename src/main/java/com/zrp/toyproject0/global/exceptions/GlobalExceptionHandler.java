package com.zrp.toyproject0.global.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(CustomException.class)
    public String handlerCustomException(CustomException e, Model model) {
        model.addAttribute("errorMessage", e.getErrorCode().getMessage());
        model.addAttribute("status", e.getErrorCode().getStatus().value());
        return "error.html";
    }

}
