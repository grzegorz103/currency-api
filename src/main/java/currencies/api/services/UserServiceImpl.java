package currencies.api.services;

import currencies.api.mappers.UserMapper;
import currencies.api.models.BankAccount;
import currencies.api.models.User;
import currencies.api.repository.BankAccountRepository;
import currencies.api.repository.UserRepository;
import currencies.api.web.dto.UserIn;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BankAccountRepository bankAccountRepository;

    private final BCryptPasswordEncoder encoder;

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, BCryptPasswordEncoder encoder, BankAccountRepository bankAccountRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    @Transactional
    public void create(UserIn userIn) {
        User mapped = userMapper.toModel(userIn);
        mapped.setCredentials(false);
        mapped.setEnabled(true);
        mapped.setPassword(encoder.encode(userIn.getPassword()));

        User save = userRepository.save(mapped);

        BankAccount bankAccountSave = BankAccount.builder()
                .number(RandomStringUtils.randomNumeric(26, 26))
                .saldos(
                        new LinkedList<>()
                )
                .user(save)
                .build();

        BankAccount bankAccount = bankAccountRepository.save(bankAccountSave);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByPesel(s).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
