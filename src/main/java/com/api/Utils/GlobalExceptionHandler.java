package com.api.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     *   Spring이 JSON 요청 데이터를 Dto 객체로 변환하려 시도.
     *   @Valid 가 필드에 선언된 검증 조건을 확인
     *   검증 조건이 만족하지 않으면 MethodArgumentNotValidException 에러를 발생
     *
     *   ControllerAdvice는 Spring MVC에서 발생하는 예외를 전역적으로 처리할 수 있는 클래스
     *   컨트롤러에서 유효성 검증에 실패하면, MethodArgumentNotValidException이 던져지고
     *   이 예외를 처리하도록 @ExceptionHandler 가 호출 됨
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> errors = new HashMap<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
