package currencies.api.web.dto;

import currencies.api.validators.AdultValidator;
import currencies.api.validators.UserTakenValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserIn {

    private String name;

    private String surname;

    @AdultValidator
    private LocalDate birthDay;

    private BigDecimal initialBalance;

    @UserTakenValidator
    private String pesel;

    private String password;

}
