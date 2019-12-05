package br.com.codenation.softlog.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.codenation.softlog.dto.request.LogRequestDTO;
import br.com.codenation.softlog.dto.response.LogAggregateResponseDTO;
import br.com.codenation.softlog.dto.response.LogResponseDTO;
import br.com.codenation.softlog.enums.OrderByEnum;
import br.com.codenation.softlog.enums.SearchForEnum;
import br.com.codenation.softlog.model.enums.Environment;
import br.com.codenation.softlog.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api")
@Api(tags = { "Logs" })
@AllArgsConstructor
public class LogResource {

	private LogService logService;

	@ApiOperation(value = "List all log aggregates", notes = "Method used to list log aggregates.")
	@GetMapping(path = "/logs", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LogAggregateResponseDTO>> search(@RequestParam(required = false) Environment environment,
			@RequestParam(required = false) OrderByEnum orderBy,
			@RequestParam(required = false) SearchForEnum searchFor,
			@RequestParam(required = false) String searchForValue, @RequestParam(defaultValue = "0") Integer startPage,
			@RequestParam(defaultValue = "2") Integer pageSize) {
		return ResponseEntity
				.ok(logService.searchLogs(environment, orderBy, searchFor, searchForValue, startPage, pageSize));
	}

	@ApiOperation(value = "Create a log entry", notes = "Method used to create a log entry. Use your API key to save logs.")
	@PostMapping(path = "/logs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LogResponseDTO> save(@Valid @RequestBody final LogRequestDTO logDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(logService.save(logDTO));
	}

	@ApiOperation(value = "Remove a log aggregate", notes = "Method used to remove a log aggregate.")
	@DeleteMapping(path = "/logs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void remove() {
		// TODO Auto-generated method stub

	}
}
