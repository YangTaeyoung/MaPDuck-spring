package com.mapduck.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// 작성자: 양태영
// 작성일: 21.11.07
// 설명: 비즈니스 로직에서 에러를 공통적으로 처리하기 위한 어드바이스 클래스
@Slf4j
@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(RestException.class)
    public ResponseEntity<Map<String, Object>> handler(RestException e) {

        log.info("[Exception] ex", e);

        Map<String, Object> resBody = new HashMap<>();
        resBody.put("message", e.getMessage());
        return new ResponseEntity<>(resBody, e.getStatus());
    }
}
