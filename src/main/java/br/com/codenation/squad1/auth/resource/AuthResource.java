package br.com.codenation.squad1.auth.resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("auth")
public class AuthResource {

	@ApiOperation("Forgot my password")
	@PostMapping(path = "forgot-password", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void forgotPassword() {
		// TODO Auto-generated method stub

	}

	@ApiOperation("Login in application")
	@PostMapping(path = "login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void login() {
		// TODO Auto-generated method stub
	}

}
