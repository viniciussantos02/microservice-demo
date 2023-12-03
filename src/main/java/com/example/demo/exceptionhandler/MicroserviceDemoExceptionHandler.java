package com.example.demo.exceptionhandler;

import com.example.demo.model.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class MicroserviceDemoExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        String userMessage = messageSource.getMessage("invalid.message", null, LocaleContextHolder.getLocale());
        String devMessage = ex.getCause().toString();

        List<Error> errorList = Arrays.asList(new Error(String.valueOf(status.value()), userMessage, devMessage));
        return super.handleExceptionInternal(ex, errorList, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Error> errorList = createErrorList(ex.getBindingResult(), String.valueOf(status.value()));
        return super.handleExceptionInternal(ex, errorList, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(RuntimeException ex) {
        logError(ex);

        String userMessage = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());

        Error error = new Error(String.valueOf(HttpStatus.BAD_REQUEST.value()), userMessage, ex.toString());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private List<Error> createErrorList(BindingResult bindingResult, String statusCode) {
        List<Error> errorList = new ArrayList<>();

        String userMessage;
        String devMessage;

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            devMessage = fieldError.toString();

            errorList.add(new Error(statusCode, userMessage, devMessage));
        }

        return errorList;
    }

    private void logError(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
        log.info(ex.getClass().getName());
    }
}
