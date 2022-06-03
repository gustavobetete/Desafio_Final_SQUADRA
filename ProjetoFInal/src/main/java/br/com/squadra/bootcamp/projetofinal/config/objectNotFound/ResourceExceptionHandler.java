package br.com.squadra.bootcamp.projetofinal.config.objectNotFound;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundExceptions.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundExceptions e){

        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(
                status.value(),
                e.getMessage());

        return ResponseEntity.status(status).body(err);
    }
}
