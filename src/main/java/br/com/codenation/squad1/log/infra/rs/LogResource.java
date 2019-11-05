package br.com.codenation.squad1.log.infra.rs;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("log")
public class LogResource {

	@ApiOperation("Buscar logs")
	@GetMapping
	public void buscar() {
		// TODO Auto-generated method stub

	}
	
	@ApiOperation("Inserir log")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void inserir() {
		// TODO Auto-generated method stub

	}
	
	@ApiOperation("Remover log")
	@DeleteMapping
	public void remover() {
		// TODO Auto-generated method stub

	}
}
