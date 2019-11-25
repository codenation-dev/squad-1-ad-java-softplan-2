package br.com.codenation.squad1.user.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.codenation.squad1.user.service.UserService;
import br.com.codenation.squad1.user.dto.UserDTO;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @ApiOperation("Create an user account")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long create(@Valid @RequestBody final UserDTO dto) {
        return userService.create(dto);
    }
}
