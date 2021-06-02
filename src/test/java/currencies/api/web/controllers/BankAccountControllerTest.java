package currencies.api.web.controllers;

import currencies.api.config.UrlConfig;
import currencies.api.services.BankAccountService;
import currencies.api.web.dto.BankAccountOut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankAccountService bankAccountService;

    private String BASE_URL = UrlConfig.API_VERSION + UrlConfig.BANK_ACCOUNT_URL;

    @Test
    @WithMockUser(username = "test", password = "pass")
    public void getBankAccountsTest() throws Exception {
        when(bankAccountService.getBankAccount(anyString())).thenReturn(new BankAccountOut());

        this.mockMvc.perform(get(BASE_URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void getBankAccountsUnauthorized() throws Exception {
        when(bankAccountService.getBankAccount(anyString())).thenReturn(new BankAccountOut());

        this.mockMvc.perform(get(BASE_URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isUnauthorized());
    }
}
