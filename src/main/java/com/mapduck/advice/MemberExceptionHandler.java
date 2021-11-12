package com.mapduck.advice;

import com.mapduck.error.MemberErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@ControllerAdvice
public class MemberExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<MemberErrorResponse> processError(Exception e){
        log.error("Exception", e);
        MemberErrorResponse response = new MemberErrorResponse();
        HttpStatus status;

        if(e instanceof MethodArgumentTypeMismatchException){
            status = HttpStatus.BAD_REQUEST;
            response.setStatusCode(status.value());
            response.setMessage("Only number is allowed");
        }else if (e instanceof DuplicatedUsernameException) {
            status = HttpStatus.BAD_REQUEST;
            response.setStatusCode(status.value());
            response.setMessage(e.getMessage());
        }
        else{
            status = HttpStatus.NOT_FOUND;
            response.setStatusCode(status.value());
            response.setMessage(e.getMessage());
        }
        response.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, status);
    }
}
