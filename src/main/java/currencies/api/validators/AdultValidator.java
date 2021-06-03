package currencies.api.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AdultValidatorImpl.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AdultValidator {
    String message() default "Incorrect birth day provided";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
