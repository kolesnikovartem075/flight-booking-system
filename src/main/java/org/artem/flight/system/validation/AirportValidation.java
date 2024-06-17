package org.artem.flight.system.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AirportValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AirportValidation {

    String message() default "Airports are the same";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
