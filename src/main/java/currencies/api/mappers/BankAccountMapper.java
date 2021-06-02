package currencies.api.mappers;

import currencies.api.models.BankAccount;
import currencies.api.web.dto.BankAccountOut;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {

    BankAccountOut toDTO(BankAccount bankAccount);

}
