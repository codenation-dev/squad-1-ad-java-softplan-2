package br.com.codenation.softlog.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.codenation.softlog.dto.request.UserRequestDTO;
import br.com.codenation.softlog.dto.response.UserResponseDTO;
import br.com.codenation.softlog.mapper.UserMapper;
import br.com.codenation.softlog.model.User;
import br.com.codenation.softlog.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private UserRepository userRepository;
	private UserMapper mapper;

	public UserResponseDTO create(final UserRequestDTO userDto) {
		User user = mapper.map(userDto);
		user.setApiKey(UUID.randomUUID().toString());
		return mapper.map(userRepository.save(user));
	}

	public Boolean isValidApiKey(String apiKey) {
		return userRepository.existsByApiKey(apiKey);
	}

}
