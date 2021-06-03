package currencies.api.web.controllers;

import currencies.api.config.UrlConfig;
import currencies.api.services.ConversionService;
import currencies.api.web.dto.ConversionIn;
import currencies.api.web.dto.ConversionOut;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(UrlConfig.API_VERSION + UrlConfig.CONVERSION_URL)
@Api(tags = "Conversions")
public class ConversionController {

    private final ConversionService conversionService;

    public ConversionController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @PostMapping
    @ApiOperation(value = "Converts source currency into indicated destined currency. Source and destined currency type must be PLN or USD")
    public ConversionOut create(@RequestBody @Valid ConversionIn conversionIn) {
        return conversionService.create(conversionIn);
    }

}
