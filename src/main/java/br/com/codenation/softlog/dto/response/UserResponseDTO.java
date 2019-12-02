package br.com.codenation.softlog.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "name", "email"})
public class UserResponseDTO {

    // TODO:
    //      - Retornar id ou token?

    @ApiModelProperty(value = "User identification", position = 1, example = "1L")
    private Long id;

    @ApiModelProperty(value = "User name", position = 2, example = "Jesus Christ")
    private String name;

    @ApiModelProperty(value = "User e-mail", position = 3, example = "admin@codenation.com")
    private String email;

}
