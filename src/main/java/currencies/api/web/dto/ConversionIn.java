package currencies.api.web.dto;

import currencies.api.models.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionIn {

    @Positive(message = "Bank account id must be positive")
    private Long bankAccountId;

    @NotNull(message = "Source currency type can not be null")
    private CurrencyType sourceCurrencyType;

    @NotNull(message = "Destined currency type can not be null")
    private CurrencyType destinedCurrencyType;

    @NotNull
    @Positive(message = "Balance must be positive1")
    private BigDecimal balance;

}
