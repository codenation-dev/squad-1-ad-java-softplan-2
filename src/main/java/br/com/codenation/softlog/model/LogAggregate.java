package br.com.codenation.softlog.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import br.com.codenation.softlog.model.enums.EnvironmentEnum;
import br.com.codenation.softlog.model.enums.Level;
import br.com.codenation.softlog.model.enums.StatusEnum;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Immutable
@Subselect("select title, description, level, api_key, source, status, environment, count(*) as events, max(id) as id, max(created_at) as created "
		// from
		+ "from log "
		// group
		+ "group by title, description, level, api_key, source, status, environment")
public class LogAggregate {

	@Id
	private Long id;

	private String title;

	private String description;

	@Enumerated(EnumType.STRING)
	private Level level;

	private String apiKey;

	private String source;

	@Enumerated(EnumType.STRING)
	private StatusEnum status;

	@Enumerated(EnumType.STRING)
	private EnvironmentEnum environment;

	private Long events;

	@CreatedDate
	private LocalDateTime created;

}
