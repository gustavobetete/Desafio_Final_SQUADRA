package br.com.squadra.bootcamp.projetofinal.config.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroValidacaoHandler {
    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroFormDto> handle(MethodArgumentNotValidException exception){

        List<ErroFormDto> errorFormDto = new ArrayList<>();
        List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();

        fieldError.forEach(e -> {

            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());

            ErroFormDto error = new ErroFormDto(e.getField(), message);
            errorFormDto.add(error);
        });

        return errorFormDto;
    }
}
