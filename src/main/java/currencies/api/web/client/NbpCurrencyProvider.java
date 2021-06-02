package currencies.api.web.client;

import currencies.api.models.CurrencyType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Objects;

@Component
public class NbpCurrencyProvider extends AbstractNbpCurrencyProvider {

    public NbpCurrencyProvider() {
        this.addBaseCurrency();
    }

    private void addBaseCurrency() {
        this.currencies.add(
                NbpCurrency.builder()
                        .code("PLN")
                        .rates(
                                Collections.singletonList(new NbpRate(BigDecimal.ONE))
                        ).build()
        );
    }

    public BigDecimal getCurrencyRate(CurrencyType currencyType) {
        return this.currencies
                .stream()
                .filter(currency -> Objects.equals(currency.getCode().toLowerCase(), currencyType.name().toLowerCase()))
                .findFirst()
                .map(currency -> currency.getRates().get(0).getMid())
                .orElseThrow(() -> new RuntimeException("Currency not found " + currencyType));
    }

}
