package br.com.codenation.softlog.dto.response;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.codenation.softlog.model.enums.EnvironmentEnum;
import br.com.codenation.softlog.model.enums.Level;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonPropertyOrder({ "id", "level", "apiKey", "source", "title", "description", "environment", "events", "created" })
public class LogAggregateResponseDTO {

	@ApiModelProperty(value = "Max log id from events", position = 1)
	private Long id;

	@Enumerated(EnumType.STRING)
	@ApiModelProperty(value = "Log severity level", position = 2, example = "ERROR", allowableValues = "INFO, DEBUG, WARNING, ERROR, FATAL")
	private Level level;

	@ApiModelProperty(value = "User API key", position = 3, example = "f0fb85c6-6165-4972-bd33-0369ebffc6ac")
	private String apiKey;

	@ApiModelProperty(value = "Log source identifier or application name", position = 4, example = "172.16.254.1")
	private String source;

	@ApiModelProperty(value = "Log title message", position = 5, example = "Error:(42, 52) java: cannot find symbol")
	private String title;

	@ApiModelProperty(value = "Log description message", position = 6, example = "location: class br.com.codenation.my-app:AuthService")
	private String description;

	@Enumerated(EnumType.STRING)
	@ApiModelProperty(value = "Application running environment", position = 7, example = "DEVELOPMENT", allowableValues = "DEVELOPMENT, HOMOLOGATION , PRODUCTION")
	private EnvironmentEnum environment;

	@ApiModelProperty(value = "Number of log events", position = 8, example = "1000")
	private Long events;

	@ApiModelProperty(value = "Last log event timestamp", position = 9, example = "2019-12-03T15:25:48.9120524")
	@CreatedDate
	private LocalDateTime created;
}