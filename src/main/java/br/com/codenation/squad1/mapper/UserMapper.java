package br.com.codenation.squad1.mapper;

import br.com.codenation.squad1.dto.request.UserRequestDTO;
import br.com.codenation.squad1.dto.response.UserResponseDTO;
import br.com.codenation.squad1.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "password", source = "password"),
    })
    User map(UserRequestDTO dto);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "name", source = "name"),
    })
    UserResponseDTO map(User user);

}
