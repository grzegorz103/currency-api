package currencies.api.config;

import currencies.api.repository.UserRepository;
import currencies.api.services.UserService;
import currencies.api.web.dto.UserIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;

@org.springframework.context.annotation.Configuration
public class Configuration {

    private final UserService userService;

    public Configuration(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private UserRepository userRepository;
    @PostConstruct
    public void addSampleUser() {
        this.userService.create(
                new UserIn("Jan", "Kowalski", LocalDate.of(1990, 5, 10), BigDecimal.valueOf(10000), "88041499999", "password")
        );
    }

}
