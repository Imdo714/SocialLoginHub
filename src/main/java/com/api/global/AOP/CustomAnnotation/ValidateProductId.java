package com.api.global.AOP.CustomAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateProductId {

    /**
     *  @Target 어노테이션을 적용할 수 있는 대상을 지정합니다.
     *  ElementType.TYPE: 클래스, 인터페이스, 열거형에 적용.
     *  ElementType.METHOD: 메서드에 적용.
     *  ElementType.FIELD: 클래스의 필드(변수)에 적용.
     *  ElementType.PARAMETER: 메서드의 매개변수에 적용.
     *  ElementType.CONSTRUCTOR: 생성자에 적용.
     *
     *  @Retention 어노테이션의 유지 기간을 지정합니다.
     *  RetentionPolicy.SOURCE: 컴파일 시 제거됨 (코드 분석용).
     *  RetentionPolicy.CLASS: 클래스 파일에 포함되지만, 런타임 시에는 사용 불가.
     *  RetentionPolicy.RUNTIME: 런타임 시에도 유지되며 리플렉션(Reflection)을 통해 접근 가능.
     *
     */
}
