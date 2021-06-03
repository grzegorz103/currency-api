package currencies.api.services;

import currencies.api.models.BankAccount;
import currencies.api.models.CurrencyType;
import currencies.api.models.Saldo;
import currencies.api.repository.BankAccountRepository;
import currencies.api.repository.SaldoRepository;
import currencies.api.utils.CurrencyConverter;
import currencies.api.web.dto.ConversionIn;
import currencies.api.web.dto.ConversionOut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class ConversionServiceImpl implements ConversionService {

    private final BankAccountRepository bankAccountRepository;

    private final SaldoRepository saldoRepository;

    private final CurrencyConverter currencyConverter;

    public ConversionServiceImpl(BankAccountRepository bankAccountRepository, CurrencyConverter currencyConverter, SaldoRepository saldoRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.currencyConverter = currencyConverter;
        this.saldoRepository = saldoRepository;
    }


    @Override
    public ConversionOut create(ConversionIn conversionIn) {
        BankAccount bankAccount = bankAccountRepository.findById(conversionIn.getBankAccountId()).orElseThrow(() -> new RuntimeException("Bank account not found"));
        Saldo sourceSaldo = this.getSaldo(bankAccount, conversionIn.getSourceCurrencyType());
        Saldo destinedSaldo = this.getSaldo(bankAccount, conversionIn.getDestinedCurrencyType());

        if (!hasSufficientBalance(sourceSaldo, conversionIn.getBalance())) {
            throw new RuntimeException("Insufficient balance on source saldo");
        }

        BigDecimal convertedBalance = currencyConverter.convert(conversionIn.getBalance(), conversionIn.getSourceCurrencyType(), conversionIn.getDestinedCurrencyType());

        BigDecimal sourceBalanceBefore = sourceSaldo.getBalance();
        sourceSaldo.setBalance(sourceSaldo.getBalance().subtract(conversionIn.getBalance()));

        BigDecimal destinedBalanceBefore = destinedSaldo.getBalance();
        destinedSaldo.setBalance(destinedSaldo.getBalance().add(convertedBalance));

        Saldo sourceSaldoSaved = saldoRepository.save(sourceSaldo);
        Saldo destinedSaldoSaved = saldoRepository.save(destinedSaldo);

        return ConversionOut.builder()
                .balance(conversionIn.getBalance())
                .sourceCurrencyType(conversionIn.getSourceCurrencyType())
                .destinedCurrencyType(conversionIn.getDestinedCurrencyType())
                .sourceSaldoBeforeConversion(sourceBalanceBefore)
                .sourceSaldoAfterConversion(sourceSaldoSaved.getBalance())
                .destinedSaldoBeforeConversion(destinedBalanceBefore)
                .destinedSaldoAfterConversion(destinedSaldoSaved.getBalance())
                .build();
    }

    private boolean hasSufficientBalance(Saldo sourceSaldo, BigDecimal balance) {
        return sourceSaldo.getBalance().compareTo(balance) >= 0;
    }

    private Saldo getSaldo(BankAccount bankAccount, CurrencyType currencyType) {
        return bankAccount.getSaldos()
                .stream()
                .filter(saldo -> saldo.getCurrencyType() == currencyType)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(currencyType + "saldo not found"));
    }

}
