package com.kargus.Customer.Website.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public String invalidEntry(IllegalStateException e, Model model) {

        model.addAttribute("error", e.getMessage());

      return "error-page";

   }
}
