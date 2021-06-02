package currencies.api.web.controllers;

import currencies.api.config.UrlConfig;
import currencies.api.models.BankAccount;
import currencies.api.services.BankAccountService;
import currencies.api.web.dto.BankAccountOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(UrlConfig.API_VERSION + UrlConfig.BANK_ACCOUNT_URL)
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public BankAccountOut getBankAccount() {
        return bankAccountService.getBankAccount("string");
    }

}
