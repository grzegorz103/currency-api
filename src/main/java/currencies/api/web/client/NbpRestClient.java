package currencies.api.web.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
