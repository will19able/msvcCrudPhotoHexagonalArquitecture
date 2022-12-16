package com.wpolog.sprintcloud.msvc.imagenes.infra.inputadapter.http.exeption;

import com.wpolog.sprintcloud.msvc.imagenes.domain.exeption.BusinessException;
import com.wpolog.sprintcloud.msvc.imagenes.domain.exeption.CodeError;
import com.wpolog.sprintcloud.msvc.imagenes.infra.inputadapter.http.responsehandler.ExeptionResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
@Slf4j
public class ExceptionGlobalResponse {

    HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
    HttpStatus unprocessableEntity = HttpStatus.UNPROCESSABLE_ENTITY;
    HttpStatus methodNotAllowed = HttpStatus.METHOD_NOT_ALLOWED;
    HttpStatus babRequest = HttpStatus.BAD_REQUEST;

    public static final String MESSAGEEXEPTION = "Exception: ";

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> runtimeException(BusinessException e) {
        String message = e.getMessage();
        log.error(message, e);
        return ExeptionResponseHandler.generateResponse(MESSAGEEXEPTION + message, internalServerError, e.codeError);

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> runtimeException(RuntimeException e) {
        String message = e.getMessage();
        log.error(message, e);
        return ExeptionResponseHandler.generateResponse(MESSAGEEXEPTION + message, internalServerError, CodeError.DEFAULT_SYSTEM_ERROR_CODE);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception e) {
        String message = e.getMessage();
        log.error(message, e);
        return ExeptionResponseHandler.generateResponse(MESSAGEEXEPTION + message, internalServerError,  CodeError.DEFAULT_SYSTEM_ERROR_CODE);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> exception(IllegalArgumentException e) {
        String message = e.getMessage();
        log.error(message, e);
        return ExeptionResponseHandler.generateResponse(MESSAGEEXEPTION + message, internalServerError,  CodeError.DEFAULT_SYSTEM_ERROR_CODE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> exception(HttpRequestMethodNotSupportedException e) {
        String message = e.getMessage();
        log.error(message, e);
        return ExeptionResponseHandler.generateResponse(MESSAGEEXEPTION + message, methodNotAllowed,  CodeError.DEFAULT_SYSTEM_ERROR_CODE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> exception(MethodArgumentNotValidException e) {
        FieldError fieldError=e.getBindingResult().getFieldError();
        String message = Optional.ofNullable(fieldError).map(FieldError::getDefaultMessage).orElse("");
        log.error(message, e);
        return ExeptionResponseHandler.generateResponse(MESSAGEEXEPTION + message, unprocessableEntity,  CodeError.DEFAULT_SYSTEM_ERROR_CODE);
    }

}
