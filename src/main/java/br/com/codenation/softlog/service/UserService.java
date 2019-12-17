package br.com.codenation.softlog.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codenation.softlog.dto.request.UserRequestDTO;
import br.com.codenation.softlog.dto.response.UserResponseDTO;
import br.com.codenation.softlog.exception.NegocioException;
import br.com.codenation.softlog.mapper.UserMapper;
import br.com.codenation.softlog.model.User;
import br.com.codenation.softlog.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapper mapper;

	public UserResponseDTO create(final UserRequestDTO userDto) {
		validateUser(userDto);
		User user = mapper.map(userDto);
		user.setApiKey(UUID.randomUUID().toString());
		return mapper.map(userRepository.save(user));
	}

	private void validateUser(UserRequestDTO userDto) {
		Boolean existsByEmail = userRepository.existsByEmail(userDto.getEmail());
		
		if (existsByEmail) {
			throw new NegocioException("Email já cadastrado");
		}
	}

	public Boolean isValidApiKey(String apiKey) {
		return userRepository.existsByApiKey(apiKey);
	}

}
