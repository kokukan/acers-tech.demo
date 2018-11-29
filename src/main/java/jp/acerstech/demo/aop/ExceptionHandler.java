package jp.acerstech.demo.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
     public ResponseEntity<Object> handleAllException(Exception e, WebRequest request) {

         return super.handleExceptionInternal(e,e.getMessage(),null, HttpStatus.INTERNAL_SERVER_ERROR,request);
     }
}
