package currencies.api.services;

import currencies.api.web.dto.UserIn;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void create(UserIn userIn);

}
