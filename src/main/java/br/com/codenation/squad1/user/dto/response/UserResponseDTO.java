package br.com.codenation.squad1.user.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "name", "email"})
public class UserResponseDTO {

    @ApiModelProperty(value = "User identification", position = 1, example = "cbd9881e-88e9-4973-bfc0-5b4fcde29574")
    private Long id;

    @ApiModelProperty(value = "User name", position = 2, example = "Jesus Christ")
    private String name;

    @ApiModelProperty(value = "User e-mail", position = 3, example = "admin@codenation.com")
    private String email;

}
