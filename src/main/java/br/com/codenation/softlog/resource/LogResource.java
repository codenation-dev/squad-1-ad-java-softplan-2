package br.com.codenation.softlog.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.codenation.softlog.dto.request.LogRequestDTO;
import br.com.codenation.softlog.dto.response.LogAggregateResponseDTO;
import br.com.codenation.softlog.dto.response.LogDetailsDTO;
import br.com.codenation.softlog.dto.response.LogResponseDTO;
import br.com.codenation.softlog.dto.response.PageDTO;
import br.com.codenation.softlog.enums.OrderByEnum;
import br.com.codenation.softlog.enums.SearchForEnum;
import br.com.codenation.softlog.model.enums.EnvironmentEnum;
import br.com.codenation.softlog.model.enums.StatusEnum;
import br.com.codenation.softlog.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api")
@Api(tags = { "Logs" })
@AllArgsConstructor
public class LogResource {

    private final LogService logService;

    @ApiOperation(value = "List all log aggregates", notes = "Method used to list log aggregates.")
    @GetMapping(path = "/logs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageDTO<LogAggregateResponseDTO>> search(
            @RequestParam(required = false) final EnvironmentEnum environment,
            @RequestParam(required = false) final OrderByEnum orderBy,
            @RequestParam(required = false) final SearchForEnum searchFor,
            @RequestParam(required = false) final String searchForValue,
            @RequestParam(defaultValue = "ACTIVE") final StatusEnum status,
            // pagination
            @RequestParam(defaultValue = "0") final Integer startPage, @RequestParam(defaultValue = "2") final Integer pageSize) {
        return ResponseEntity.ok(
                logService.searchLogs(environment, orderBy, searchFor, searchForValue, status, startPage, pageSize));
    }

    @ApiOperation(value = "Create a log entry", notes = "Method used to create a log entry. Use your API key to save logs.")
    @PostMapping(path = "/logs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LogResponseDTO> save(@Valid @RequestBody final LogRequestDTO logDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(logService.save(logDTO));
    }

    @ApiOperation(value = "Remove a log aggregate", notes = "Method used to remove a log aggregate.")
    @DeleteMapping(path = "/logs")
    public void remove(@RequestParam List<Long> ids) {
        logService.remove(ids);
    }
    
    @ApiOperation(value = "Archive a log aggregate", notes = "Method used to archive a log aggregate.")
	@PatchMapping(path = "/logs")
	public void archiveById(@RequestParam List<Long> ids) {
		logService.archiveById(ids);
	}
    
    @ApiOperation(value = "Details a log aggregate", notes = "Method used to detail a log aggregate.")
  	@GetMapping(path = "/logs/{logId}")
  	public ResponseEntity<LogDetailsDTO> detailsById(@PathVariable final Long logId) {
  		return ResponseEntity.status(HttpStatus.OK).body(logService.detailsById(logId));
  	}
}
