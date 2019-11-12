package br.com.codenation.squad1.user.infra.rs;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.codenation.squad1.user.application.UserAccountService;
import br.com.codenation.squad1.user.infra.dto.UserAccountDTO;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("user")
public class UserAccountResource {

    @Autowired
    private UserAccountService usuarioService;

    @ApiOperation("Cadastrar usuário na aplicação")
    @PostMapping(path = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long create(@Valid @RequestBody final UserAccountDTO userDTO) {
        return usuarioService.create(userDTO);
    }
}
