package currencies.api.services;

import currencies.api.web.dto.UserIn;
import currencies.api.web.dto.UserOut;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserOut create(UserIn userIn);

    UserOut findByPesel(String pesel);

}
