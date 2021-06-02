package currencies.api.validators;

import currencies.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UserTakenValidatorImpl implements ConstraintValidator<UserTakenValidator, String> {
    private final UserRepository userRepository;

    @Autowired
    public UserTakenValidatorImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UserTakenValidator constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        return !userRepository.existsByPesel(value);
    }
}
