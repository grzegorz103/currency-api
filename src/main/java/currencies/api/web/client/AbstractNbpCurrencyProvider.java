package currencies.api.web.client;

import currencies.api.models.CurrencyType;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractNbpCurrencyProvider {

    protected List<NbpCurrency> currencies = Collections.synchronizedList(new LinkedList<>());

    public abstract BigDecimal getCurrencyRate(CurrencyType currencyType);

    public void addCurrency(NbpCurrency currency) {
        this.currencies.add(currency);
    }

    public void clearCurrencies() {
        this.currencies.clear();
    }

    public List<NbpCurrency> getCurrencies() {
        return currencies;
    }
}
