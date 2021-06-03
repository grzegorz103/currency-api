package currencies.api.services;

import currencies.api.web.dto.ConversionIn;
import currencies.api.web.dto.ConversionOut;

public interface ConversionService {

    ConversionOut create(ConversionIn conversionIn);

}
