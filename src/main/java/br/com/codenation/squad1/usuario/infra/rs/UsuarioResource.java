package br.com.codenation.squad1.usuario.infra.rs;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.codenation.squad1.usuario.application.UsuarioService;
import br.com.codenation.squad1.usuario.infra.dto.CadastroUsuarioDTO;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;

	@ApiOperation("Cadastrar usuário na aplicação")
	@PostMapping(path = "cadastrar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> cadastrar(@Valid @RequestBody CadastroUsuarioDTO usuario) {
		usuarioService.cadastrar(usuario);

		return ResponseEntity.created(null).build();
	}
}
