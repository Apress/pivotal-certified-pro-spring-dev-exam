package com.ps.exs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by iuliana.cosmina on 10/13/15.
 */
@ControllerAdvice
public class RestExceptionProcessor {

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public JsonError exception(UserException ex) {
        return new JsonError(ex.getMessage());
    }
}
