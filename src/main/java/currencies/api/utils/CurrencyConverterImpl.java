package currencies.api.utils;

import currencies.api.models.CurrencyType;
import currencies.api.web.client.AbstractNbpCurrencyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CurrencyConverterImpl implements CurrencyConverter{

    private final AbstractNbpCurrencyProvider currencyProvider;

    public CurrencyConverterImpl(AbstractNbpCurrencyProvider currencyProvider) {
        this.currencyProvider = currencyProvider;
    }

    @Override
    public BigDecimal convert(BigDecimal currency, CurrencyType sourceCurrencyType, CurrencyType destinedCurrencyType) {
        BigDecimal sourceCurrencyRate = currencyProvider.getCurrencyRate(sourceCurrencyType);
        BigDecimal destinedCurrencyRate = currencyProvider.getCurrencyRate(destinedCurrencyType);

        return currency.multiply(sourceCurrencyRate)
                .divide(destinedCurrencyRate, 2, RoundingMode.DOWN)
                .setScale(2, RoundingMode.DOWN);
    }
}
