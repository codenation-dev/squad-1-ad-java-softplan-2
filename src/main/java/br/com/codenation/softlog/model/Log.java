package br.com.codenation.softlog.model;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.codenation.softlog.model.enums.Environment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import br.com.codenation.softlog.model.enums.Level;
import br.com.codenation.softlog.model.enums.Status;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 50)
	private String userToken;

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
	private Environment environment;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Level level;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(name = "created_at", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdAt;

	@Column(name = "modified_at")
	@LastModifiedDate
	private LocalDateTime modifiedAt;

}
