package br.com.squadra.bootcamp.projetofinal.config.unauthorized;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class UnauthorizedResourceExceptionHandler {

    @ExceptionHandler(UnauthorizedExceptions.class)
    public ResponseEntity<UnauthorizedStandardError> objectNotFound(UnauthorizedExceptions e){

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        UnauthorizedStandardError err = new UnauthorizedStandardError(
                status.value(),
                e.getMessage());

        return ResponseEntity.status(status).body(err);
    }
}
