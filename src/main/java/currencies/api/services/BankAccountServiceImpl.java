package currencies.api.services;

import currencies.api.mappers.BankAccountMapper;
import currencies.api.models.BankAccount;
import currencies.api.repository.BankAccountRepository;
import currencies.api.web.dto.BankAccountOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    private final BankAccountMapper bankAccountMapper;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, BankAccountMapper bankAccountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountMapper = bankAccountMapper;
    }

    @Override
    public BankAccountOut getBankAccount(String pesel) {
        bankAccountRepository.findAll().forEach(e-> System.out.println(e));
        return bankAccountMapper.toDTO(
                bankAccountRepository.findBankAccountByUser_Pesel(pesel)
                        .orElseThrow(() -> new RuntimeException("Bank account not found"))
        );

    }
}
