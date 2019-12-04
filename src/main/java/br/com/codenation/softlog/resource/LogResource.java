package br.com.codenation.softlog.resource;

import java.util.List;

import br.com.codenation.softlog.dto.request.LogRequestDTO;
import br.com.codenation.softlog.dto.response.LogResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.codenation.softlog.model.LogAggregate;
import br.com.codenation.softlog.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api")
@Api(tags = { "Logs" })
@AllArgsConstructor
public class LogResource {

	private LogService logService;

	@ApiOperation(
			value = "List all log aggregates",
			notes = "Method used to list log aggregates.")
	@GetMapping(
			path = "/logs",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<LogAggregate> list() {

		// TODO: retornar DTO de LogAggregate

		return logService.listAggregate();
	}

	@ApiOperation(
			value = "Create a log entry",
			notes = "Method used to create a log entry. Use your API key to save logs.")
	@PostMapping(
			path = "/logs",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LogResponseDTO> save(@Valid @RequestBody final LogRequestDTO logDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(
				logService.save(logDTO));
	}

	@ApiOperation(
			value = "Remove a log aggregate",
			notes = "Method used to remove a log aggregate.")
	@DeleteMapping(
			path = "/logs",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public void remove() {
		// TODO Auto-generated method stub

	}
}
