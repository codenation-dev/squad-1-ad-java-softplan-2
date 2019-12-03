package br.com.codenation.softlog.service;

import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import br.com.codenation.softlog.dto.request.LoginDTO;
import br.com.codenation.softlog.model.User;
import br.com.codenation.softlog.repository.UserRepository;
import br.com.codenation.softlog.security.jwt.CredencialsJWT;
import br.com.codenation.softlog.security.jwt.SecurityJWT;

@Service
@AllArgsConstructor
public class AuthService {

	private UserRepository userRepository;
	private SecurityJWT segurancaJWT;

	public String login(LoginDTO loginDTO) {
		String passwordMd5 = DigestUtils.md5DigestAsHex(loginDTO.getPassword().getBytes());
		Optional<User> userOptional = userRepository.findByEmailAndPassword(loginDTO.getEmail(), passwordMd5);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			CredencialsJWT credenciais = segurancaJWT.createCredencials(user.getName(), user.getEmail(), "USER");
			return credenciais.getToken();
		}
		return null;
	}

}
