package br.com.codenation.squad1.auth.rs;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("auth")
public class AuthResource {

	@ApiOperation("Login na aplicação")
	@PostMapping(path = "login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void login() {
		// TODO Auto-generated method stub

	}
	
	@ApiOperation("Esqueci minha senha")
	@PostMapping(path = "esqueci-senha", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void esqueciSenha() {
		// TODO Auto-generated method stub

	}
	
	@ApiOperation("Cadastrar usuário na aplicação")
	@PostMapping(path = "cadastrar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar() {
		// TODO Auto-generated method stub

	}
}
