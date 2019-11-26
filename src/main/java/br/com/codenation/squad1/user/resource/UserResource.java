package br.com.codenation.squad1.user.resource;

import javax.validation.Valid;

import br.com.codenation.squad1.user.dto.request.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.codenation.squad1.user.service.UserService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @ApiOperation("Register new user account")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@Valid @RequestBody final UserRegistrationDTO user) {
        userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
