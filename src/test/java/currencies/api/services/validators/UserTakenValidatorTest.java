package currencies.api.services.validators;

import currencies.api.repository.UserRepository;
import currencies.api.validators.UserTakenValidatorImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserTakenValidatorTest {

    @InjectMocks
    private UserTakenValidatorImpl userTakenValidator;

    @Mock
    private UserRepository userRepository;

    @Test
    public void userNotExistsTest() {
        when(userRepository.existsByPesel(anyString())).thenReturn(false);
        assertThat(userTakenValidator.isValid("123", null)).isTrue();
    }

    @Test
    public void userAlreadyExistsTest() {
        when(userRepository.existsByPesel(anyString())).thenReturn(true);
        assertThat(userTakenValidator.isValid("123", null)).isFalse();
    }

}
