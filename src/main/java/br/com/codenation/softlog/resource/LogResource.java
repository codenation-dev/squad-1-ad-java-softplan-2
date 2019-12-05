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
import org.springframework.web.bind.annotation.RestController;

import br.com.codenation.softlog.dto.request.LogRequestDTO;
import br.com.codenation.softlog.dto.response.LogAggregateResponseDTO;
import br.com.codenation.softlog.dto.response.LogResponseDTO;
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

    private final LogService logService;

    @ApiOperation(value = "List all log aggregates", notes = "Method used to list log aggregates.")
    @GetMapping(path = "/logs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LogAggregateResponseDTO>> list() {
        return ResponseEntity.ok(logService.listAggregate());
    }

    @ApiOperation(value = "Create a log entry", notes = "Method used to create a log entry. Use your API key to save logs.")
    @PostMapping(path = "/logs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LogResponseDTO> save(@Valid @RequestBody final LogRequestDTO logDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                logService.save(logDTO));
    }

    @ApiOperation(value = "Remove a log aggregate", notes = "Method used to remove a log aggregate.")
    @DeleteMapping(path = "/logs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void remove() {
        // TODO Auto-generated method stub

    }

    @ApiOperation(value = "List all log aggregates by environment", notes = "Method used to list log aggregates by environment.")
    @GetMapping(path = "/logs/enviroment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LogAggregateResponseDTO>> listByEnvironment(@Valid @RequestBody final Environment enviroment) {
        return ResponseEntity.ok(logService.listAggregateByEnviroment(enviroment));
    }

    @ApiOperation(value = "Order log aggregates by environment", notes = "Method used to order log aggregates environment.")
    @GetMapping(path = "/logs/enviroment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LogAggregateResponseDTO>> listAllOrderByEnvironment(@Valid @RequestBody final Environment enviroment) {
        return ResponseEntity.ok(logService.listAllOrderByEnviroment(enviroment));
    }
}
