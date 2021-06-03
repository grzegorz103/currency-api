package currencies.api.services;

import currencies.api.mappers.UserMapper;
import currencies.api.models.User;
import currencies.api.repository.UserRepository;
import currencies.api.web.dto.UserOut;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private UserMapper userMapper;

    @Test
    public void findByPeselTest() {
        String testPesel = "999999999";
        when(userRepository.findByPesel(anyString())).thenReturn(Optional.of(new User()));
        when(userMapper.toDTO(any(User.class))).thenReturn(new UserOut("test", "test", testPesel, 1L));

        UserOut expected = userService.findByPesel(testPesel);

        assertNotNull(expected);
        assertThat(expected.getPesel()).isEqualTo(testPesel);
    }

}
