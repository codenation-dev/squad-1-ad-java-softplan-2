package br.com.codenation.squad1.user.mapper.request;

import br.com.codenation.squad1.user.dto.request.UserRegistrationDTO;
import br.com.codenation.squad1.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRegistrationDTOMapper {

    User map(UserRegistrationDTO dto);

}
