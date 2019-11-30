package br.com.codenation.softlog.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.codenation.softlog.model.LogAggregate;
import br.com.codenation.softlog.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api")
@Api(tags = { "Logs" })
@AllArgsConstructor
public class LogResource {

	@Autowired
	private LogService logService;

	@ApiOperation(value = "List all log aggregates", notes = "Method used to list log aggregates.")
	@GetMapping(path = "/logs", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<LogAggregate> list() {
		return logService.listAggregate();

	}

	@ApiOperation(value = "Create a log entry", notes = "Method used to create a log entry.")
	@PostMapping(path = "/logs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void create() {
		// TODO Auto-generated method stub

	}

	@ApiOperation(value = "Remove a log aggregate", notes = "Method used to remove a log aggregate.")
	@DeleteMapping(path = "/logs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void remove() {
		// TODO Auto-generated method stub

	}
}
