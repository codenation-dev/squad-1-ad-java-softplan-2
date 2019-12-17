package br.com.codenation.softlog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import br.com.codenation.softlog.dto.request.LoginRequestDTO;
import br.com.codenation.softlog.exception.NegocioException;
import br.com.codenation.softlog.model.User;
import br.com.codenation.softlog.repository.UserRepository;
import br.com.codenation.softlog.security.jwt.CredencialsJWT;
import br.com.codenation.softlog.security.jwt.SecurityJWT;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SecurityJWT segurancaJWT;

	public String login(LoginRequestDTO loginRequestDTO) {
		String passwordMd5 = DigestUtils.md5DigestAsHex(loginRequestDTO.getPassword().getBytes());
		Optional<User> userOptional = userRepository.findByEmailAndPassword(loginRequestDTO.getEmail(), passwordMd5);
		User user = userOptional.orElseThrow(() -> new NegocioException("Usuário ou senha inválidos"));
		CredencialsJWT credenciais = segurancaJWT.createCredencials(user.getName(), user.getEmail(), "USER");
		return credenciais.getToken();
	}

}
