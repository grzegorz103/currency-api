package currencies.api.services;

import currencies.api.web.dto.ConversionIn;
import currencies.api.web.dto.ConversionOut;

public interface ConversionService {

    void create(ConversionIn conversionIn);

}
