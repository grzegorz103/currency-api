package currencies.api.config;

import currencies.api.services.UserService;
import currencies.api.web.dto.UserIn;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

@org.springframework.context.annotation.Configuration
public class Configuration {

    private final UserService userService;

    public Configuration(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void addSampleUser() {
        this.userService.create(
                new UserIn("Jan", "Kowalski", LocalDate.of(1990, 5, 10), BigDecimal.valueOf(10000), "88041499999")
        );
    }

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo("Currency API","API allows to convert currency between specified types.<br>Currency types are fetched from NBP bank during application startup. <br>Application uses in built H2 memory database. <br>Every person has one bank account which is created automatically during user registration. <br>One default test user account is created at startup with PESEL 88041499999 with balance of 10000 PLN and 0 USD","v1","",null,"","", Collections.emptyList());
    }

}
