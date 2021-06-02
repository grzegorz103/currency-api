package currencies.api.services;

import currencies.api.mappers.UserMapper;
import currencies.api.models.User;
import currencies.api.repository.UserRepository;
import currencies.api.web.dto.UserIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void create(UserIn userIn) {
        User mapped = userMapper.toModel(userIn);
        mapped.setCredentials(false);
        mapped.setEnabled(false);


        userRepository.save(mapped);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
