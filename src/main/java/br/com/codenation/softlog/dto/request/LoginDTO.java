package br.com.codenation.softlog.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginDTO {

	@ApiModelProperty(example = "joao@codenation.com")
	private String email;
	
	@ApiModelProperty(example = "codenation")
	private String password;
}
