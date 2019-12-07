package br.com.codenation.softlog.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.codenation.softlog.model.enums.EnvironmentEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import br.com.codenation.softlog.model.enums.Level;
import br.com.codenation.softlog.model.enums.StatusEnum;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 50)
	private String source;

	@NotBlank
	@Size(max = 255)
	private String title;

	@NotBlank
	@Size(max = 500)
	private String description;

	@NotNull
	@Enumerated(EnumType.STRING)
	private EnvironmentEnum environment;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Level level;

	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusEnum status;

	@NotBlank
	@Column(name = "api_key", updatable = false)
	private String apiKey;

}
