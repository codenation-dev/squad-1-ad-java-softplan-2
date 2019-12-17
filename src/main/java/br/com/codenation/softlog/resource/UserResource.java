package br.com.codenation.softlog.resource;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.codenation.softlog.dto.request.UserRequestDTO;
import br.com.codenation.softlog.dto.response.UserResponseDTO;
import br.com.codenation.softlog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api")
@Api(tags = { "Users" })
@AllArgsConstructor
public class UserResource {

	private UserService userService;

	@ApiOperation(
			value = "Create new user account",
			notes = "Method used to register new user.")
	@PostMapping(
			path = "/users",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody final UserRequestDTO userDTO) {
		UserResponseDTO createdUser = userService.create(userDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
}
