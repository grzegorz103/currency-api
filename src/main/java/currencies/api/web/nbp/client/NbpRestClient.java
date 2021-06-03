package currencies.api.web.nbp.client;

import currencies.api.web.nbp.dto.NbpCurrency;
import currencies.api.web.nbp.providers.AbstractNbpCurrencyProvider;
import currencies.api.web.nbp.providers.NbpCurrencyProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.HashMap;

@Component
public class NbpRestClient {

    private AbstractNbpCurrencyProvider currencyProvider;

    private HashMap<String, BigDecimal> currencies = new HashMap<>();

    public NbpRestClient(NbpCurrencyProvider currencyProvider,
                         @Value("${nbp.url}") String nbpUrl) {
        this.currencyProvider = currencyProvider;
        this.fetchCurrencies(nbpUrl);
    }

    public void fetchCurrencies(String nbpUrl) {
        WebClient webClient = WebClient.builder()
                .baseUrl(nbpUrl)
                .build();

        NbpCurrency usd = webClient.get()
                .uri("usd")
                .retrieve()
                .bodyToMono(NbpCurrency.class)
                .block();

        currencyProvider.addCurrency(usd);
    }

}
