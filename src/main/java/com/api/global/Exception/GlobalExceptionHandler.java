package com.api.global.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
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

    // Valid 예외
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.info("MethodArgumentNotValidException 예외 발생");
        // BindingResult를 가져옴
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> errors = new HashMap<>();

        // 필드에러를 하나씩 처리해 JSON 형태로 변환
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // 커스텀 예외 클래스
    @ExceptionHandler(value = AroundHubException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(AroundHubException e) {
        HttpHeaders responseHeaders = new HttpHeaders();

        Map<String, String> map = new HashMap<>();
        map.put("error type", e.getHttpStatusType());
        map.put(
                "error code",
                Integer.toString(e.getHttpStatusCode())); // Map<String, Object>로 설정하면 toString 불필요
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, responseHeaders, e.getHttpStatus());
    }

    // IllegalArgumentException 잘못된 파라미터 예외
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.info("IllegalArgumentException 예외 발생");
        log.info("IllegalArgumentException = {}",  ex.getMessage());
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // DB 문제
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.info("DataIntegrityViolationException 예외 발생");
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    // Redis 연결 실패 예외 처리
    @ExceptionHandler(RedisConnectionFailureException.class)
    public ResponseEntity<Map<String, String>> handleRedisConnectionFailureException(RedisConnectionFailureException ex) {
        log.info("RedisConnectionFailureException 예외 발생");

        // 에러 메시지 생성
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());

        // 409 Conflict 상태 코드로 응답
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }


}
