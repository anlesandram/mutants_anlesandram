package com.mercadolibre.service.mutants.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String ERROR_MESSAGE = "There was an error trying to process the request";

    @ExceptionHandler
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {

        log.error("Request {} Error {} ", request.toString(), ex.getStackTrace());
        return handleExceptionInternal(ex, ERROR_MESSAGE,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
