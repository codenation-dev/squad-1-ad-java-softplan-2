package br.com.codenation.softlog.mapper;

import br.com.codenation.softlog.dto.request.UserRequestDTO;
import br.com.codenation.softlog.dto.response.UserResponseDTO;
import br.com.codenation.softlog.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "password", source = "password"),
    })
    User map(UserRequestDTO dto);

    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "apiKey", source = "apiKey")
    })
    UserResponseDTO map(User user);

}
