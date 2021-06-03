package currencies.api.mappers;

import currencies.api.models.User;
import currencies.api.web.dto.UserIn;
import currencies.api.web.dto.UserOut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toModel(UserIn userIn);

    @Mapping(source = "bankAccount.id", target = "bankAccountId")
    UserOut toDTO(User user);

}
