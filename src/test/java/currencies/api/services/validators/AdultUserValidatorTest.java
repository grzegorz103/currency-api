package currencies.api.services.validators;

import currencies.api.validators.AdultValidatorImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class AdultUserValidatorTest {

    private AdultValidatorImpl adultUserValidator = new AdultValidatorImpl();

    @Test
    public void isValidTest() {
        assertThat(adultUserValidator.isValid(LocalDate.parse("1990-11-12"), null)).isTrue();
    }

    @Test
    public void isNotAdultTest() {
        assertThat(adultUserValidator.isValid(LocalDate.parse("2012-11-12"), null)).isFalse();
    }

}
