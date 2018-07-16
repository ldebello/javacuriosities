package ar.com.javacuriosities.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldMapping {

    String name();

    String type() default "";

    String defaultValue() default "";

    int length() default 255;

    boolean isPk() default false;

    boolean nullable() default false;
}
