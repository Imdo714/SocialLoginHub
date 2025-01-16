package com.api.global.Exception;

import org.springframework.http.HttpStatus;

public class AroundHubException extends Exception{

    /**
     *   AroundHubException은 Exception을 상속받아 예외를 받아줌
     */

    private Constants.ExceptionClass exceptionClass;
    private HttpStatus httpStatus;

    // AroundHubException 생성자
    public AroundHubException(Constants.ExceptionClass exceptionClass, HttpStatus httpStatus, String message) {
        super(exceptionClass.toString() + message);
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
    }

    public Constants.ExceptionClass getExceptionClass() {
        return exceptionClass;
    }

    // 몇번대 코드 ex) 400, 401
    public int getHttpStatusCode() {
        return httpStatus.value();
    }

    // 코드 타입 ex) BAD_REQUEST, NOT_FOUND
    public String getHttpStatusType() {
        return httpStatus.getReasonPhrase();
    }

    // httpStatus 객체 자체를 리턴
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
