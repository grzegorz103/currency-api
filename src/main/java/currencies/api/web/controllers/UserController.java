package currencies.api.web.controllers;

import currencies.api.config.UrlConfig;
import currencies.api.services.UserService;
import currencies.api.web.dto.UserIn;
import currencies.api.web.dto.UserOut;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("isAnonymous()")
    public UserOut create(@Valid @RequestBody UserIn userIn) {
        return userService.create(userIn);
    }

    @GetMapping("/{pesel}")
    public UserOut findById(@PathVariable("pesel") String pesel) {
        return userService.findByPesel(pesel);
    }

}
