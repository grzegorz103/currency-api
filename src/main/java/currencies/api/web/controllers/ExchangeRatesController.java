package currencies.api.web.controllers;

import currencies.api.config.UrlConfig;
import currencies.api.models.CurrencyType;
import currencies.api.web.client.AbstractNbpCurrencyProvider;
import currencies.api.web.client.NbpCurrency;
import currencies.api.web.client.NbpCurrencyProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(UrlConfig.API_VERSION + UrlConfig.EXCHANGE_RATES_URL)
@Api(tags = "Exchange rates")
public class ExchangeRatesController {

    private final AbstractNbpCurrencyProvider nbpCurrencyProvider;

    public ExchangeRatesController(NbpCurrencyProvider nbpCurrencyProvider) {
        this.nbpCurrencyProvider = nbpCurrencyProvider;
    }

    @GetMapping
    @ApiOperation(value = "Returns NBP currency list")
    public List<NbpCurrency> getCurrencies() {
        return this.nbpCurrencyProvider.getCurrencies();
    }

    @GetMapping("/{code}")
    @ApiOperation(value = "Returns NBP currency by code")
    public BigDecimal getCurrency(@PathVariable("code") CurrencyType code) {
        return this.nbpCurrencyProvider.getCurrencyRate(code);
    }

}
