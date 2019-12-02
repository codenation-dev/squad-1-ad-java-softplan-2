package br.com.codenation.softlog.resource;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/auth")
@Api(tags = {"Auth"})
@AllArgsConstructor
public class AuthResource {

	@ApiOperation(
			value = "Forgot my password",
			notes = "Method used to reset user password."
	)
	@PostMapping(
			path = "/forgot-password",
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public void forgotPassword() {
		// TODO Auto-generated method stub

	}

	@ApiOperation(
			value = "Login in application",
			notes = "Method used to login in application."
	)
	@PostMapping(
			path = "/login",
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public void login() {
		// TODO Auto-generated method stub
	}

}
