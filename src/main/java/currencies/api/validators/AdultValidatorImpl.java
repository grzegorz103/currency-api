package currencies.api.validators;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class AdultValidatorImpl implements ConstraintValidator<AdultValidator, LocalDate> {

    @Override
    public void initialize(AdultValidator constraintAnnotation) {

    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return ChronoUnit.YEARS.between(value, LocalDate.now()) >= 18;
    }
}
