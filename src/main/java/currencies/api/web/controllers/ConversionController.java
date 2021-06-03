package currencies.api.web.controllers;

import currencies.api.services.ConversionService;
import currencies.api.web.dto.ConversionIn;
import currencies.api.web.dto.ConversionOut;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
@Api(tags = "Conversions")
public class ConversionController {

    private final ConversionService conversionService;

    public ConversionController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @PostMapping
    public void create(@RequestBody @Valid ConversionIn conversionIn) {
        conversionService.create(conversionIn);
    }

}
