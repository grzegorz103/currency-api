package currencies.api.web.dto;

import currencies.api.validators.AdultValidator;
import currencies.api.validators.UserTakenValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserIn {

    @NotBlank(message = "Name can not be empty")
    private String name;

    @NotBlank(message = "Surname can not be empty")
    private String surname;

    @AdultValidator
    private LocalDate birthDay;

    @NotNull
    @Positive(message = "Initial balance must be positive")
    private BigDecimal initialBalance;

    @UserTakenValidator
    private String pesel;

}
