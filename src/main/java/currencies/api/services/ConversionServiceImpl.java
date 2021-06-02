package currencies.api.services;

import currencies.api.models.BankAccount;
import currencies.api.models.CurrencyType;
import currencies.api.models.Saldo;
import currencies.api.repository.BankAccountRepository;
import currencies.api.repository.SaldoRepository;
import currencies.api.utils.CurrencyConverter;
import currencies.api.web.dto.ConversionIn;
import currencies.api.web.dto.ConversionOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class ConversionServiceImpl implements ConversionService{

    private final BankAccountRepository bankAccountRepository;

    private final SaldoRepository saldoRepository;

    private final CurrencyConverter currencyConverter;

    public ConversionServiceImpl(BankAccountRepository bankAccountRepository, CurrencyConverter currencyConverter, SaldoRepository saldoRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.currencyConverter = currencyConverter;
        this.saldoRepository = saldoRepository;
    }


    @Override
    public void create(ConversionIn conversionIn) {
        BankAccount bankAccount = bankAccountRepository.findById(conversionIn.getBankAccountId()).orElseThrow(() -> new RuntimeException("Bank account not found"));
        Optional<Saldo> sourceSaldo = this.getSaldo(bankAccount, conversionIn.getSourceCurrencyType());
        Optional<Saldo> destinedSaldo = this.getSaldo(bankAccount, conversionIn.getDestinedCurrencyType());

        if(!hasSufficientBalance(sourceSaldo)) {
            throw new RuntimeException("Insufficient balance");
        }

        if(sourceSaldo.isPresent() && destinedSaldo.isPresent()) {
            BigDecimal convertedBalance = currencyConverter.convert(conversionIn.getBalance(), conversionIn.getSourceCurrencyType(), conversionIn.getDestinedCurrencyType());

            Saldo source = sourceSaldo.get();
            source.setBalance(source.getBalance().subtract(conversionIn.getBalance()));

            Saldo destined = destinedSaldo.get();
            destined.setBalance(destined.getBalance().add(convertedBalance));

            saldoRepository.save(source);
            saldoRepository.save(destined);
        }
    }

    private boolean hasSufficientBalance(Optional<Saldo> sourceSaldo) {
        return sourceSaldo.map(Saldo::getBalance)
                .filter(balance -> balance.signum() > 0)
                .isPresent();
    }

    private Optional<Saldo> getSaldo(BankAccount bankAccount, CurrencyType currencyType) {
        return bankAccount.getSaldos()
                .stream()
                .filter(saldo -> saldo.getCurrencyType() == currencyType)
                .findFirst();
    }

}
