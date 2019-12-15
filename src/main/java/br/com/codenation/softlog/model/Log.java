package br.com.codenation.softlog.model;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.codenation.softlog.dto.response.LogDetailsDTO;
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

@SqlResultSetMapping(name = "LogDetailsDTO", classes = {
        @ConstructorResult(targetClass = LogDetailsDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "level", type = String.class),
                        @ColumnResult(name = "apiKey"),
                        @ColumnResult(name = "source"),
                        @ColumnResult(name = "title"),
                        @ColumnResult(name = "description"),
                        @ColumnResult(name = "user"),
                        @ColumnResult(name = "events", type = Long.class),
                        @ColumnResult(name = "created", type = LocalDateTime.class)
                })
})
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
