package br.com.codenation.softlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codenation.softlog.model.LogAggregate;
import br.com.codenation.softlog.repository.LogAggregateRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LogService {

	@Autowired
	private LogAggregateRepository logAggregateRepository;

	public void save() {

	}

	public void remove() {

	}

	public List<LogAggregate> listAggregate() {
		return logAggregateRepository.findByTitle("Log info");

	}
}
