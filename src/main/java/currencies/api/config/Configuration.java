package currencies.api.config;

import currencies.api.repository.UserRepository;
import currencies.api.services.UserService;
import currencies.api.web.dto.UserIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;

@org.springframework.context.annotation.Configuration
public class Configuration {

    private final UserService userService;
    private final UserRepository userRepository;

    public Configuration(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void addSampleUser() {
        this.userService.create(
                new UserIn("Jan", "Kowalski", LocalDate.of(1990, 5, 10), BigDecimal.valueOf(10000), "88041499999")
        );
    }

    @Bean
    public Docket awesomeApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

}
