package org.niks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class RepositoryExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<Object> handleCustomException(RepositoryException e) {
        Map<String, Object> details = new LinkedHashMap<>();
        details.put("timestamp", LocalDateTime.now());
        details.put("message", e.getMessage());

        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }
}
