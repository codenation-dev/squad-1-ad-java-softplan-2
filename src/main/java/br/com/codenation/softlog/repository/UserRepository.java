package br.com.codenation.softlog.repository;

import java.util.Optional;

import br.com.codenation.softlog.dto.response.UserResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codenation.softlog.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmailAndPassword(String email, String password);

	Boolean existsByApiKey(String apiKey);

}
