package br.com.codenation.squad1.log.resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("log")
public class LogResource {

	@ApiOperation("List logs")
	@GetMapping
	public void list() {
		// TODO Auto-generated method stub

	}

	@ApiOperation("Create a log")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create() {
		// TODO Auto-generated method stub

	}

	@ApiOperation("Remove a log")
	@DeleteMapping
	public void remove() {
		// TODO Auto-generated method stub

	}
}
