package currencies.api.utils;

import currencies.api.models.CurrencyType;

import java.math.BigDecimal;

public interface CurrencyConverter {

    BigDecimal convert(BigDecimal currency, CurrencyType sourceCurrencyType, CurrencyType destinedCurrencyType);

}
