package com.api.global.Exception;

public class Constants {

    /**
     *   enum ExceptionClass : 예외 유형을 구분하기 위해 enum을 사용 그러면 toString으로 확인하면
     *   PRODUCT선택시 PRODUCT Exception. SIGN 선택시 SIGN Exception. 처험 나온다.
     */

    public enum ExceptionClass {
        PRODUCT("Product"),
        SIGN("Sign");

        private String exceptionClass;

        ExceptionClass(String exceptionClass) {
            this.exceptionClass = exceptionClass;
        }

        public String getExceptionClass() {
            return exceptionClass;
        }

        @Override
        public String toString() {
            return getExceptionClass() + " Exception. ";
        }
    }

}
