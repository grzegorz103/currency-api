package currencies.api.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import currencies.api.config.UrlConfig;
import currencies.api.services.UserService;
import currencies.api.web.dto.UserIn;
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
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private String BASE_URL = UrlConfig.API_VERSION + UrlConfig.USER_URL;

    @Test
    public void getAllCharactersTest() throws Exception {
        doNothing().when(userService).create(any(UserIn.class));

        this.mockMvc.perform(post(BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(new UserIn("testName", "testSurname", BigDecimal.TEN))))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

    @Test
    public void getAllCharactersNoBodyTest() throws Exception {
        doNothing().when(userService).create(any(UserIn.class));

        this.mockMvc.perform(post(BASE_URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }
}

