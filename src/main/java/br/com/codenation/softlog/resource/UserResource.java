package br.com.codenation.softlog.resource;

import javax.validation.Valid;

import br.com.codenation.softlog.dto.request.UserRequestDTO;
import br.com.codenation.softlog.dto.response.UserResponseDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.codenation.softlog.service.UserService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
@Api(tags = {"Users"})
public class UserResource {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Register new user account", notes = "Use your token to register new logs")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody final UserRequestDTO user) {
        UserResponseDTO registeredUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
}
