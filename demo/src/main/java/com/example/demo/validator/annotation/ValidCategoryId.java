package com.example.demo.validator.annotation;

import com.example.demo.validator.ValidCategoryIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidCategoryIdValidator.class)
@Documented
public @interface ValidCategoryId {
    String message() default "Hãy chọn Category";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
