package currencies.api.services;

import currencies.api.mappers.UserMapper;
import currencies.api.models.BankAccount;
import currencies.api.models.CurrencyType;
import currencies.api.models.Saldo;
import currencies.api.models.User;
import currencies.api.repository.BankAccountRepository;
import currencies.api.repository.SaldoRepository;
import currencies.api.repository.UserRepository;
import currencies.api.web.dto.UserIn;
import currencies.api.web.dto.UserOut;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final SaldoRepository saldoRepository;

    private final BankAccountRepository bankAccountRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, BankAccountRepository bankAccountRepository, SaldoRepository saldoRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.saldoRepository = saldoRepository;
    }

    @Override
    public UserOut create(UserIn userIn) {
        User mapped = userMapper.toModel(userIn);

        bankAccountRepository.findBankAccountByUser_Pesel(userIn.getPesel());

        mapped.setCredentials(false);
        mapped.setEnabled(true);

        User userSaved = userRepository.save(mapped);

        BankAccount bankAccountSave = BankAccount.builder()
                .number(RandomStringUtils.randomNumeric(26, 26))
                .user(userSaved)
                .build();

        BankAccount bankAccount = bankAccountRepository.save(bankAccountSave);
        userSaved.setBankAccount(bankAccount);

        Saldo plnSaldo = Saldo.builder().balance(userIn.getInitialBalance()).bankAccount(bankAccount).currencyType(CurrencyType.PLN).build();
        Saldo usdSaldo = Saldo.builder().balance(BigDecimal.ZERO).bankAccount(bankAccount).currencyType(CurrencyType.USD).build();

        saldoRepository.save(plnSaldo);
        saldoRepository.save(usdSaldo);

        return userMapper.toDTO(userSaved);
    }

    @Override
    public UserOut findByPesel(String pesel) {
        return userMapper.toDTO(
                userRepository.findByPesel(pesel).orElseThrow(() -> new RuntimeException("User not found"))
        );
    }

}
