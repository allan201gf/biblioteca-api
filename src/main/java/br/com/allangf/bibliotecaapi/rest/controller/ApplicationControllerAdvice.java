package br.com.allangf.bibliotecaapi.rest.controller;

import br.com.allangf.bibliotecaapi.domain.exception.RuleOfException;
import br.com.allangf.bibliotecaapi.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RuleOfException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRuleOfException(RuleOfException ex) {
        String errorMessage = ex.getMessage();
        return new ApiErrors(errorMessage);
    }

}
