package br.com.codenation.squad1.user.application;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codenation.squad1.user.infra.dto.UserAccountDTO;
import br.com.codenation.squad1.user.infra.repository.UserAccountRepository;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public Long create(final UserAccountDTO userDTO) {
        userDTO.setToken(UUID.randomUUID().toString());
        return userAccountRepository.save(userDTO.getUserAccount()).getId();
    }
}
