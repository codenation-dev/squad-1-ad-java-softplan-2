package br.com.codenation.softlog.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.com.codenation.softlog.dto.request.LogRequestDTO;
import br.com.codenation.softlog.dto.response.LogAggregateResponseDTO;
import br.com.codenation.softlog.dto.response.LogResponseDTO;
import br.com.codenation.softlog.mapper.LogAggregateMapper;
import br.com.codenation.softlog.mapper.LogMapper;
import br.com.codenation.softlog.model.Log;
import br.com.codenation.softlog.model.enums.Environment;
import br.com.codenation.softlog.repository.LogAggregateRepository;
import br.com.codenation.softlog.repository.LogRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LogService {

    private final LogRepository logRepository;
    private final LogAggregateRepository logAggregateRepository;
    private final LogMapper logMapper;
    private final LogAggregateMapper aggregateMapper;
    private final UserService userService;

    public LogResponseDTO save(final LogRequestDTO logDTO) {
        if (apiKeyNotValid(logDTO.getApiKey())) {
            // TODO: - Customizar Exceptions
            throw new RuntimeException("ApiKey not valid!");
        }
        final Log log = logMapper.map(logDTO);
        return logMapper.map(logRepository.save(log));
    }

    private Boolean apiKeyNotValid(final String apiKey) {
        return !userService.isValidApiKey(apiKey);
    }

    public void remove() {

    }

    public List<LogAggregateResponseDTO> listAggregate() {
        return aggregateMapper.map(logAggregateRepository.findAll());
    }

    public List<LogAggregateResponseDTO> listAggregateByEnviroment(@Valid final Environment environment) {
        return aggregateMapper.map(logAggregateRepository.findByEnvironment(environment));
    }

    public List<LogAggregateResponseDTO> listAllOrderByEnviroment(@Valid final Environment enviroment) {
        final List<LogAggregateResponseDTO> list = aggregateMapper.map(logAggregateRepository.findAll());
        list.sort((log1, log2) -> log1.getEnvironment().compareTo(log2.getEnvironment()));
        return list;

    }
}
