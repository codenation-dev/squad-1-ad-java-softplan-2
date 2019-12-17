package br.com.codenation.softlog.dto.response;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@JsonPropertyOrder({ "id", "level", "apiKey", "source", "title", "description", "user", "events", "created" })
public class LogDetailsDTO {
	@ApiModelProperty(value = "Max log id from events", position = 1)
	private Long id;
	
	//@Enumerated(EnumType.STRING)
	@ApiModelProperty(value = "Log severity level", position = 2, example = "ERROR", allowableValues = "INFO, DEBUG, WARNING, ERROR, FATAL")
	private String level;

	@ApiModelProperty(value = "User API key", position = 3, example = "f0fb85c6-6165-4972-bd33-0369ebffc6ac")
	private String apiKey;

	@ApiModelProperty(value = "Log source identifier or application name", position = 4, example = "172.16.254.1")
	private String source;

	@ApiModelProperty(value = "Log title message", position = 5, example = "Error:(42, 52) java: cannot find symbol")
	private String title;

	@ApiModelProperty(value = "Log description message", position = 6, example = "location: class br.com.codenation.my-app:AuthService")
	private String description;

	@ApiModelProperty(value = "User name", position = 7, example = "João")
	private String user;
	
	@ApiModelProperty(value = "Number of log events", position = 8, example = "1000")
	private Long events;

	@ApiModelProperty(value = "Last log event timestamp", position = 9, example = "2019-12-03T15:25:48.9120524")
	@CreatedDate
	private LocalDateTime created;
	
	
}
