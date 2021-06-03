package currencies.api.web.dto;

import currencies.api.models.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaldoOut {

    private Long id;

    private BigDecimal balance;

    private CurrencyType currencyType;
}
