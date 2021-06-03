package currencies.api.web.dto;

import currencies.api.models.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConversionOut {

    private BigDecimal balance;

    private CurrencyType sourceCurrencyType;

    private CurrencyType destinedCurrencyType;

    private BigDecimal sourceSaldoBeforeConversion;

    private BigDecimal sourceSaldoAfterConversion;

    private BigDecimal destinedSaldoBeforeConversion;

    private BigDecimal destinedSaldoAfterConversion;

}
