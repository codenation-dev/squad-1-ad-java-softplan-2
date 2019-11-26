package br.com.codenation.squad1.user.service;

import br.com.codenation.squad1.user.dto.request.UserRegistrationDTO;
import br.com.codenation.squad1.user.mapper.request.UserRegistrationDTOMapper;

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
    private UserRegistrationDTOMapper mapper;

    public void create(final UserRegistrationDTO userDto) {
        User user = mapper.map(userDto);
        // TODO: Revisar sobre Token
        user.setToken(UUID.randomUUID().toString());
        userRepository.save(user);
    }

}
