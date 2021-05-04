package com.springboot.demo.exception;

import com.springboot.demo.entities.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Student> handleStudentNotFoundException(RecordNotFoundException ex, WebRequest request) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NameAlreadyExistException.class)
    public final ResponseEntity<Student> handleStudentNotFoundException(NameAlreadyExistException ex, WebRequest request) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }



}
