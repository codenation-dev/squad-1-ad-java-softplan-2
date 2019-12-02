package br.com.codenation.softlog.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import br.com.codenation.softlog.model.enums.Environment;
import br.com.codenation.softlog.model.enums.Level;
import br.com.codenation.softlog.model.enums.Status;
import lombok.Data;

@Data
@Entity
@Immutable
@Subselect("select title, description, level, api_key, source, status, environment, count(*) as events, max(id) as id "
		// from
		+ "from log "
		// group
		+ "group by title, description, level, api_key, source, status, environment")
public class LogAggregate {

	private String title;
	private String description;

	@Enumerated(EnumType.STRING)
	private Level level;

	private String apiKey;

	private String source;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Enumerated(EnumType.STRING)
	private Environment environment;

	private Long events;

	@Id
	private Long id;

//	private LocalDateTime createdAt;

}
