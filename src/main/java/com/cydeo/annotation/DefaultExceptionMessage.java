package com.cydeo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// This is how we can created annotation.
// When ever the methods annotated with this annotation. This annotation will throw an exception when any exception happens in the methods we are going to see a massage what ever this annotation describe.
@Target(ElementType.METHOD) // Define the target that where to implement the annotation.
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultExceptionMessage {

    String defaultMessage() default "";

}