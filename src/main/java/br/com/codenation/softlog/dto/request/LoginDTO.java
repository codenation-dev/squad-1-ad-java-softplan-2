package br.com.codenation.softlog.dto.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@JsonPropertyOrder({ "email", "password" })
public class LoginDTO {

	@NotBlank
	@Email
	@Size(max = 100)
	@ApiModelProperty(
			value = "User e-mail",
			position = 1,
			example = "admin@codenation.com",
			required = true)
	private String email;

	@NotBlank
	@Size(min = 3, max = 50)
	@ApiModelProperty(
			value = "User password",
			position = 2,
			example = "jesus123",
			required = true)
	private String password;

}
