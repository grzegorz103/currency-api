package currencies.api.mappers;

import currencies.api.models.Saldo;
import currencies.api.web.dto.SaldoOut;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaldoMapper {

    SaldoOut toDTO(Saldo saldo);

}
