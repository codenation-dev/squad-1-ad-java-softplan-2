package br.com.codenation.softlog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import br.com.codenation.softlog.dto.request.LogRequestDTO;
import br.com.codenation.softlog.dto.response.LogAggregateResponseDTO;
import br.com.codenation.softlog.dto.response.LogResponseDTO;
import br.com.codenation.softlog.enums.OrderByEnum;
import br.com.codenation.softlog.enums.SearchForEnum;
import br.com.codenation.softlog.mapper.LogAggregateMapper;
import br.com.codenation.softlog.mapper.LogMapper;
import br.com.codenation.softlog.model.Log;
import br.com.codenation.softlog.model.LogAggregate;
import br.com.codenation.softlog.model.enums.Environment;
import br.com.codenation.softlog.model.enums.Level;
import br.com.codenation.softlog.repository.LogRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LogService {

	private LogRepository logRepository;
	private LogMapper logMapper;
	private LogAggregateMapper aggregateMapper;
	private UserService userService;
	private EntityManager entityManager;

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

	public List<LogAggregateResponseDTO> searchLogs(Environment environment, OrderByEnum orderBy,
			SearchForEnum searchFor, String searchForValue, Integer startPage, Integer pageSize) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<LogAggregate> query = builder.createQuery(LogAggregate.class);
		Root<LogAggregate> root = query.from(LogAggregate.class);
		List<Predicate> predicates = new ArrayList<>();

		addEnvironmentFilter(root, predicates, builder, environment);
		if (searchFor != null && searchForValue != null) {
			addDescriptionFilter(root, predicates, builder, searchFor, searchForValue);
			addLevelFilter(root, predicates, builder, searchFor, searchForValue);
			addSourceFilter(root, predicates, builder, searchFor, searchForValue);
		}

		Predicate where = builder.and(predicates.toArray(new Predicate[predicates.size()]));

		query.select(root).where(where);

		List<LogAggregate> logs = entityManager.createQuery(query).getResultList();

		return aggregateMapper.map(logs);
	}

	private void addSourceFilter(Root<LogAggregate> root, List<Predicate> predicates, CriteriaBuilder builder,
			SearchForEnum searchFor, String searchForValue) {
		// TODO Auto-generated method stub

	}

	private void addEnvironmentFilter(Root<LogAggregate> root, List<Predicate> predicates, CriteriaBuilder builder,
			Environment environment) {
		if (environment != null) {
			Path<String> path = root.get("environment");
			predicates.add(builder.equal(path, environment));
		}
	}

	private void addDescriptionFilter(Root<LogAggregate> root, List<Predicate> predicates, CriteriaBuilder builder,
			SearchForEnum searchFor, String searchForValue) {
		if (searchFor.equals(SearchForEnum.DESCRIPTION)) {
			Path<String> titlePath = root.get("title");
			Predicate titleSearch = builder.like(builder.lower(titlePath), addPercentCharacter(searchForValue));

			Path<String> descriptionPath = root.get("description");
			Predicate descriptionSearch = builder.like(builder.lower(descriptionPath),
					addPercentCharacter(searchForValue));

			predicates.add(builder.or(titleSearch, descriptionSearch));
		}
	}

	private void addLevelFilter(Root<LogAggregate> root, List<Predicate> predicates, CriteriaBuilder builder,
			SearchForEnum searchFor, String searchForValue) {
		if (searchFor.equals(SearchForEnum.LEVEL)) {
			Optional<Level> levelOptional = Level.getEnumByValue(searchForValue);
			if (levelOptional.isPresent()) {
				Path<String> path = root.get("level");
				Predicate search = builder.equal(path, levelOptional.get());

				predicates.add(search);
			}
		}
	}

	private String addPercentCharacter(String title) {
		return String.format("%%%s%%", title.toLowerCase());
	}
}
