package com.example.DSCatalog.domain.validacao;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = { UserInsertValidator.class })
@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface UserInsertValid {

	String message() default "erro de validação";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
