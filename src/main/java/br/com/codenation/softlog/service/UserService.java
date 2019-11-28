package br.com.codenation.softlog.service;

import br.com.codenation.softlog.repository.UserRepository;
import br.com.codenation.softlog.dto.request.UserRequestDTO;
import br.com.codenation.softlog.dto.response.UserResponseDTO;
import br.com.codenation.softlog.mapper.UserMapper;

import br.com.codenation.softlog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper mapper;

    public UserResponseDTO create(final UserRequestDTO userDto) {
        User user = mapper.map(userDto);

//        TODO:    - Revisar regra sobre Token
//                 - Nomes diferentes para cada map? (toUser() e toUserResponseDTO)

        user.setToken(UUID.randomUUID().toString());
        User createdUser = userRepository.save(user);
        return mapper.map(createdUser);
    }

}
