package currencies.api.utils;

import currencies.api.models.CurrencyType;
import currencies.api.web.nbp.providers.AbstractNbpCurrencyProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyConverterImplTest {

    @InjectMocks
    private CurrencyConverterImpl currencyConverter;

    @Mock
    private AbstractNbpCurrencyProvider currencyProvider;

    @Test
    public void convertPLNtoUSDTest(){
        when(currencyProvider.getCurrencyRate(CurrencyType.PLN)).thenReturn(BigDecimal.valueOf(1.00));
        when(currencyProvider.getCurrencyRate(CurrencyType.USD)).thenReturn(BigDecimal.valueOf(3.50));

        BigDecimal result = currencyConverter.convert(BigDecimal.TEN, CurrencyType.PLN, CurrencyType.USD);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(BigDecimal.valueOf(2.85));
    }

    @Test
    public void convertUSDtoPLNTest(){
        when(currencyProvider.getCurrencyRate(CurrencyType.USD)).thenReturn(BigDecimal.valueOf(3.50));
        when(currencyProvider.getCurrencyRate(CurrencyType.PLN)).thenReturn(BigDecimal.valueOf(1.00));

        BigDecimal result = currencyConverter.convert(BigDecimal.TEN, CurrencyType.USD, CurrencyType.PLN);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(new BigDecimal("35.00"));
    }

}
