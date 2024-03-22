package net.rightpair.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface UseCase {
    /**
     * 헥사고날 아키텍쳐에서 사용되는 논리적 컴포넌트 이름
     * 아키텍쳐의 유즈케이스로써 사용될 클래스
     * @return 컴포넌트의 이름 또는 공백
     */
    @AliasFor(annotation = Component.class)
    String value() default "";
}
