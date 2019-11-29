package br.com.codenation.softlog.resource;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api")
@Api(tags = {"Logs"})
@AllArgsConstructor
public class LogResource {

	@ApiOperation(
			value = "List all log aggregates",
			notes = "Method used to list log aggregates."
	)
	@GetMapping(
			path = "/logs",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public void list() {
		// TODO Auto-generated method stub

	}

	@ApiOperation(
			value = "Create a log entry",
			notes = "Method used to create a log entry."
	)
	@PostMapping(
			path = "/logs",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public void create() {
		// TODO Auto-generated method stub

	}

	@ApiOperation(
			value = "Remove a log aggregate",
			notes = "Method used to remove a log aggregate."
	)
	@DeleteMapping(
			path = "/logs",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public void remove() {
		// TODO Auto-generated method stub

	}
}
