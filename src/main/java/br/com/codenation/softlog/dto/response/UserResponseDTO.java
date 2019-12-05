package br.com.codenation.softlog.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonPropertyOrder({"name", "email", "apiKey"})
public class UserResponseDTO {

    @ApiModelProperty(
            value = "User name",
            position = 1,
            example = "Jesus Christ")
    private String name;

    @ApiModelProperty(
            value = "User e-mail",
            position = 2,
            example = "admin@codenation.com")
    private String email;

    @ApiModelProperty(
            value = "User API key",
            position = 3,
            example = "f0fb85c6-6165-4972-bd33-0369ebffc6ac")
    private String apiKey;

}
