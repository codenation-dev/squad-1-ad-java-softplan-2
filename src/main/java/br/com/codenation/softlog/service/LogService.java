package br.com.codenation.softlog.service;

import java.util.List;

import br.com.codenation.softlog.dto.request.LogRequestDTO;
import br.com.codenation.softlog.dto.response.LogResponseDTO;
import br.com.codenation.softlog.mapper.LogMapper;
import br.com.codenation.softlog.model.Log;
import br.com.codenation.softlog.repository.LogRepository;
import org.springframework.stereotype.Service;

import br.com.codenation.softlog.model.LogAggregate;
import br.com.codenation.softlog.repository.LogAggregateRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LogService {

	private LogRepository logRepository;
	private LogAggregateRepository logAggregateRepository;
	private LogMapper mapper;

	public LogResponseDTO save(LogRequestDTO logDTO) {
		Log log = mapper.map(logDTO);
	 	return mapper.map(logRepository.save(log));
	}

	public void remove() {

	}

	public List<LogAggregate> listAggregate() {
		return logAggregateRepository.findAll();
	}
}
