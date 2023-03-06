package pro.b2b2b.germ.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.b2b2b.germ.exception.OrganizationNotFoundException;

@ControllerAdvice
public class OrganizationNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(OrganizationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String organizationNotFoundHandler(OrganizationNotFoundException ex) {
        return ex.getMessage();
    }
}
