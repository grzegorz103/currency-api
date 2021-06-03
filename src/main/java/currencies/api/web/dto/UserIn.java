package currencies.api.web.dto;

import currencies.api.validators.AdultValidator;
import currencies.api.validators.UserTakenValidator;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(example = "1990-01-10")
    private LocalDate birthDay;

    @NotNull
    @Positive(message = "Initial balance must be positive")
    @ApiModelProperty(example = "10000")
    private BigDecimal initialBalance;

    @UserTakenValidator
    @ApiModelProperty(example = "66020699999")
    private String pesel;

}
