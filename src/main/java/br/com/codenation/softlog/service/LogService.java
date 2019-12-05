package br.com.codenation.softlog.service;

import java.util.List;

import br.com.codenation.softlog.dto.request.LogRequestDTO;
import br.com.codenation.softlog.dto.response.LogAggregateResponseDTO;
import br.com.codenation.softlog.dto.response.LogResponseDTO;
import br.com.codenation.softlog.mapper.LogAggregateMapper;
import br.com.codenation.softlog.mapper.LogMapper;
import br.com.codenation.softlog.model.Log;
import br.com.codenation.softlog.repository.LogRepository;
import org.springframework.stereotype.Service;

import br.com.codenation.softlog.repository.LogAggregateRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LogService {

	private LogRepository logRepository;
	private LogAggregateRepository logAggregateRepository;
	private LogMapper logMapper;
	private LogAggregateMapper aggregateMapper;
	private UserService userService;

	public LogResponseDTO save(LogRequestDTO logDTO) {
		if (apiKeyNotValid(logDTO.getApiKey())) {
			// TODO: - Customizar Exceptions
			throw new RuntimeException("ApiKey not valid!");
		}
		Log log = logMapper.map(logDTO);
		return logMapper.map(logRepository.save(log));
	}

	private Boolean apiKeyNotValid(String apiKey) {
		return !userService.isValidApiKey(apiKey);
	}

	public void remove() {

	}

	public List<LogAggregateResponseDTO> listAggregate() {
		return aggregateMapper.map(logAggregateRepository.findAll());
	}
}
