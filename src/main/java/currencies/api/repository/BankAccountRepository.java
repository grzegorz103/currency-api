package currencies.api.repository;

import currencies.api.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Optional<BankAccount> findBankAccountByUser_Pesel(String pesel);

}
