package br.com.codenation.softlog.service;


import org.springframework.stereotype.Service;
import br.com.codenation.softlog.dto.request.LoginRequestDTO;
import br.com.codenation.softlog.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

	private UserRepository userRepository;


	public String login(LoginRequestDTO loginRequestDTO) {

		return null;
	}

}
