package currencies.api.web.controllers;

import currencies.api.config.UrlConfig;
import currencies.api.services.BankAccountService;
import currencies.api.web.dto.BankAccountOut;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UrlConfig.API_VERSION + UrlConfig.BANK_ACCOUNT_URL)
@Api(tags = "Bank accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Returns specified bank account with its subaccounts")
    public BankAccountOut getBankAccount(@PathVariable("id") @ApiParam(example = "1") Long id) {
        return bankAccountService.getBankAccount(id);
    }

}
