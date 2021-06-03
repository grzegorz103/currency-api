package currencies.api.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import currencies.api.config.UrlConfig;
import currencies.api.models.CurrencyType;
import currencies.api.services.ConversionService;
import currencies.api.web.dto.BankAccountOut;
import currencies.api.web.dto.ConversionIn;
import currencies.api.web.dto.ConversionOut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ConversionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConversionService conversionService;

    private String BASE_URL = UrlConfig.API_VERSION + UrlConfig.CONVERSION_URL;

    @Test
    public void getBankAccountsTest() throws Exception {
        when(conversionService.create(any(ConversionIn.class))).thenReturn(new ConversionOut());

        ConversionIn conversionIn = new ConversionIn(1L, CurrencyType.PLN, CurrencyType.USD, BigDecimal.TEN);

        this.mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(conversionIn)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

}
