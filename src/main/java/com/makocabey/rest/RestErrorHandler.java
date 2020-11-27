package com.makocabey.rest;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.makocabey.rest.Exceptions.ChangeModelNotFoundException;
import com.makocabey.rest.Exceptions.CurrencyLessThanZeroException;
import com.makocabey.rest.Exceptions.CustomErrorResponse;
import com.makocabey.rest.Exceptions.DatabaseEmptyException;
import com.makocabey.rest.Exceptions.DateNotFoundException;
import com.makocabey.rest.Exceptions.EndDateAfterStartDateException;
import com.makocabey.rest.Exceptions.EntityElementDuplicatedException;
import com.makocabey.rest.Exceptions.IncorrectParityCodeFormatException;
import com.makocabey.rest.Exceptions.ParityCodeNotFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value =  {DatabaseEmptyException.class, 
								ChangeModelNotFoundException.class,
								DateNotFoundException.class})
	public ResponseEntity<CustomErrorResponse> customDatabaseEmpty(Exception ex, WebRequest request) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setStatus(HttpStatus.NOT_FOUND.value());
        errors.setError("Not Found");
        errors.setDetail(ex.getMessage());
        errors.setPath(request.getDescription(false).substring(4));
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

    }
	
	
	
	@ExceptionHandler(value = {EntityElementDuplicatedException.class,
								CurrencyLessThanZeroException.class,
								IncorrectParityCodeFormatException.class,
								ParityCodeNotFoundException.class,
								EndDateAfterStartDateException.class})
	public ResponseEntity<CustomErrorResponse> customEntityElementDuplicated(Exception ex, WebRequest request) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        errors.setError("Request Error");
        errors.setDetail(ex.getMessage());
        errors.setPath(request.getDescription(false).substring(4));

        return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
    }
}
