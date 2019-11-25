package br.com.codenation.squad1.user.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.codenation.squad1.user.service.UserAccountService;
import br.com.codenation.squad1.user.dto.UserAccountDTO;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("user")
public class UserAccountResource {

    @Autowired
    private UserAccountService userAccountService;

    @ApiOperation("Create an user account")
    @PostMapping(path = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long create(@Valid @RequestBody final UserAccountDTO dto) {
        return userAccountService.create(dto);
    }
}
