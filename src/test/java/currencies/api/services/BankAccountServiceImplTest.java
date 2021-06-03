package currencies.api.services;

import currencies.api.mappers.BankAccountMapper;
import currencies.api.models.BankAccount;
import currencies.api.repository.BankAccountRepository;
import currencies.api.web.dto.BankAccountOut;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankAccountServiceImplTest {

    @InjectMocks
    private BankAccountServiceImpl bankAccountService;

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Spy
    private BankAccountMapper bankAccountMapper;

    @Test
    public void findByIdTest() {
        String testBankAccountNumber = "12345";
        when(bankAccountRepository.findById(anyLong())).thenReturn(Optional.of(new BankAccount()));
        when(bankAccountMapper.toDTO(any(BankAccount.class))).thenReturn(new BankAccountOut(1L, testBankAccountNumber, Collections.emptyList()));

        BankAccountOut expected = bankAccountService.getBankAccount(1L);

        assertThat(expected.getNumber()).isEqualTo(testBankAccountNumber);
    }


}
