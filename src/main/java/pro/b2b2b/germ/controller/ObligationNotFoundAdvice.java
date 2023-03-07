package pro.b2b2b.germ.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.b2b2b.germ.exception.ObligationNotFoundException;
import pro.b2b2b.germ.exception.OrganizationNotFoundException;

@ControllerAdvice
public class ObligationNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ObligationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String obligationNotFoundHandler(ObligationNotFoundException ex) {
        return ex.getMessage();
    }
}

