package br.com.codenation.squad1.user.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codenation.squad1.user.dto.UserDTO;
import br.com.codenation.squad1.user.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Long create(final UserDTO userDTO) {
        userDTO.setToken(UUID.randomUUID().toString());
        return userRepository.save(userDTO.getUserAccount()).getId();
    }
}
