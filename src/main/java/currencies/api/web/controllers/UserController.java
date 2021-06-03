package currencies.api.web.controllers;

import currencies.api.config.UrlConfig;
import currencies.api.services.UserService;
import currencies.api.web.dto.UserIn;
import currencies.api.web.dto.UserOut;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(UrlConfig.API_VERSION + UrlConfig.USER_URL)
@Api(tags = "Users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Creates new user with one bank account with PLN and USD subaccounts. Initial balance is set to PLN account")
    public UserOut create(@Valid @RequestBody UserIn userIn) {
        return userService.create(userIn);
    }

    @GetMapping("/{pesel}")
    @ApiOperation(value = "Returns user by given PESEL identifier")
    public UserOut findById(@PathVariable("pesel") @ApiParam(example = "88041499999") String pesel) {
        return userService.findByPesel(pesel);
    }

}
