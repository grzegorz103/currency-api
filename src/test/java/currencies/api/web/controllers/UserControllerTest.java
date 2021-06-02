package currencies.api.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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
    public void createUserTest() throws Exception {
        doNothing().when(userService).create(any(UserIn.class));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        this.mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new UserIn("testName", "testSurname", LocalDate.of(1960, 10, 10), BigDecimal.TEN, "91919112123", "password"))))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

    @Test
    public void createUserNoBodyTest() throws Exception {
        doNothing().when(userService).create(any(UserIn.class));

        this.mockMvc.perform(post(BASE_URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }
}

