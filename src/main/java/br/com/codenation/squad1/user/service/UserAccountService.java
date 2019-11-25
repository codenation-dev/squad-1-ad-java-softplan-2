package br.com.codenation.squad1.user.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codenation.squad1.user.dto.UserAccountDTO;
import br.com.codenation.squad1.user.repository.UserAccountRepository;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public Long create(final UserAccountDTO userDTO) {
        userDTO.setToken(UUID.randomUUID().toString());
        return userAccountRepository.save(userDTO.getUserAccount()).getId();
    }
}
