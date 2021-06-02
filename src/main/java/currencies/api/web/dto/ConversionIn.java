package currencies.api.web.dto;

import currencies.api.models.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionIn {

    private Long bankAccountId;

    private CurrencyType sourceCurrencyType;

    private CurrencyType destinedCurrencyType;

    private BigDecimal balance;

}
