package currencies.api.mappers;

import currencies.api.models.User;
import currencies.api.web.dto.UserIn;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toModel(UserIn userIn);

}
