package br.com.codenation.squad1.user.service;

import br.com.codenation.squad1.user.dto.request.UserRequestDTO;
import br.com.codenation.squad1.user.dto.response.UserResponseDTO;
import br.com.codenation.squad1.user.mapper.UserMapper;

import br.com.codenation.squad1.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codenation.squad1.user.repository.UserRepository;

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
