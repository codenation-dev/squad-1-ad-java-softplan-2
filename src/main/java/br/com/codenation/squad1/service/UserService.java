package br.com.codenation.squad1.service;

import br.com.codenation.squad1.dto.request.UserRequestDTO;
import br.com.codenation.squad1.dto.response.UserResponseDTO;
import br.com.codenation.squad1.mapper.UserMapper;

import br.com.codenation.squad1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codenation.squad1.repository.UserRepository;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper mapper;

    public UserResponseDTO create(final UserRequestDTO userDto) {
        User user = mapper.map(userDto);
        // TODO: Revisar sobre Token
        user.setToken(UUID.randomUUID().toString());
        User createdUser = userRepository.save(user);
        return mapper.map(createdUser);
    }

}
