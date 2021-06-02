package currencies.api.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserTakenValidatorImpl.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserTakenValidator {
    String message() default "User already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
