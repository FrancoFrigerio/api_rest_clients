package ar.com.frigeriofranco.practic.controller.handlecontroller;

import ar.com.frigeriofranco.practic.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.*;


@ControllerAdvice
@Slf4j
public class ValidationHandleController extends ResponseEntityExceptionHandler {


    @Value("error.item.provided.not.found")
    private String itemNotFound;

    @Value("error.http-method")
    private String methodIncorrect;

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName,message);
        });
        return new ResponseEntity<>(errors,BAD_REQUEST);
    }
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.badRequest().body(messageSource.getMessage(methodIncorrect,null,Locale.getDefault()));
    }

    @ExceptionHandler(RolesNotFoundException.class)
    public ResponseEntity<String> rolesNotFound(RolesNotFoundException rolesNotFoundException){
        return  ResponseEntity.badRequest().body(rolesNotFoundException.getErrorMessage());
    }

    @ExceptionHandler(BadCredentialsExceptions.class)
    public ResponseEntity<?>badRequestException(BadCredentialsExceptions badCredentialsExceptions){
       PayLoadException payload = new PayLoadException(badCredentialsExceptions.getMessage(),HttpStatus.BAD_REQUEST,new Date());
        return ResponseEntity.badRequest().body(payload);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?>elementNotFound(NoSuchElementException e){
        PayLoadException payload = new PayLoadException(e.getMessage(),HttpStatus.NOT_FOUND,new Date());
        return new ResponseEntity<>(payload,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?>entityNotFound(EntityNotFoundException e){
    PayLoadException payload = new PayLoadException(messageSource.getMessage(itemNotFound,null, Locale.getDefault()),HttpStatus.NOT_FOUND,new Date());
        return new ResponseEntity<>(payload,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<?>userAlreadyExist(UserAlreadyExist e){
        PayLoadException payload = new PayLoadException(e.getMessage(),HttpStatus.BAD_REQUEST,new Date());
        return new ResponseEntity<>(payload,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
