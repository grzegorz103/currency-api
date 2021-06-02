package currencies.api.services;

import currencies.api.web.dto.BankAccountOut;

public interface BankAccountService {

    BankAccountOut getBankAccount(String username);

}
