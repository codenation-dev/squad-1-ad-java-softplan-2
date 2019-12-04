package br.com.codenation.softlog.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.codenation.softlog.dto.request.LoginRequestDTO;
import br.com.codenation.softlog.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/auth")
@Api(tags = { "Auth" })
@AllArgsConstructor
public class AuthResource {

	private AuthService authService;

	@ApiOperation(
			value = "Forgot my password",
			notes = "Method used to reset user password.")
	@PostMapping(
			path = "/forgot-password",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void forgotPassword() {
		// TODO Auto-generated method stub

	}

	@ApiOperation(
			value = "Login in application",
			notes = "Method used to login in application.")
	@PostMapping(
			path = "/login",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> login(@RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse response) {
		String token = authService.login(loginRequestDTO);
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}

}
