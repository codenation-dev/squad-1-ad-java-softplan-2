package br.com.codenation.softlog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import br.com.codenation.softlog.dto.request.LogRequestDTO;
import br.com.codenation.softlog.dto.response.LogAggregateResponseDTO;
import br.com.codenation.softlog.dto.response.LogResponseDTO;
import br.com.codenation.softlog.dto.response.PageDTO;
import br.com.codenation.softlog.enums.OrderByEnum;
import br.com.codenation.softlog.enums.SearchForEnum;
import br.com.codenation.softlog.mapper.LogAggregateMapper;
import br.com.codenation.softlog.mapper.LogMapper;
import br.com.codenation.softlog.model.Log;
import br.com.codenation.softlog.model.LogAggregate;
import br.com.codenation.softlog.model.enums.EnvironmentEnum;
import br.com.codenation.softlog.model.enums.Level;
import br.com.codenation.softlog.model.enums.StatusEnum;
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

	public PageDTO<LogAggregateResponseDTO> searchLogs(EnvironmentEnum environment, OrderByEnum orderBy,
			SearchForEnum searchFor, String searchForValue, StatusEnum status, Integer startPage, Integer pageSize) {

		List<LogAggregateResponseDTO> logsDTO = searchLogsData(environment, orderBy, searchFor, searchForValue, status,
				startPage, pageSize);

		Long logsCount = searchLogsCount(environment, searchFor, searchForValue, status);

		// return datas
		return new PageDTO<LogAggregateResponseDTO>(logsDTO, logsCount);
	}

	private Long searchLogsCount(EnvironmentEnum environment, SearchForEnum searchFor, String searchForValue,
			StatusEnum status) {

		CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cQuery = cBuilder.createQuery(Long.class);
		Root<LogAggregate> root = cQuery.from(LogAggregate.class);

		Predicate where = addFilters(root, cBuilder, environment, searchFor, searchForValue, status);

		cQuery.select(cBuilder.count(root)).where(where);

		TypedQuery<Long> query = entityManager.createQuery(cQuery);

		// execute query, get Logs
		Long count = query.getSingleResult();

		return count;
	}

	private List<LogAggregateResponseDTO> searchLogsData(EnvironmentEnum environment, OrderByEnum orderBy,
			SearchForEnum searchFor, String searchForValue, StatusEnum status, Integer startPage, Integer pageSize) {

		CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<LogAggregate> cQuery = cBuilder.createQuery(LogAggregate.class);
		Root<LogAggregate> root = cQuery.from(LogAggregate.class);

		Predicate where = addFilters(root, cBuilder, environment, searchFor, searchForValue, status);

		cQuery.select(root).where(where);

		addOrder(cQuery, cBuilder, root, orderBy);

		// pagination
		TypedQuery<LogAggregate> query = entityManager.createQuery(cQuery);
		query.setFirstResult(startPage);
		query.setMaxResults(pageSize);

		// execute query, get Logs
		List<LogAggregate> logs = query.getResultList();
		// tranform to DTO
		List<LogAggregateResponseDTO> logsDTO = aggregateMapper.map(logs);

		return logsDTO;
	}

	private Predicate addFilters(Root<LogAggregate> root, CriteriaBuilder cBuilder, EnvironmentEnum environment,
			SearchForEnum searchFor, String searchForValue, StatusEnum status) {

		List<Predicate> predicates = new ArrayList<>();

		addStatusFilter(root, predicates, cBuilder, status);
		addEnvironmentFilter(root, predicates, cBuilder, environment);
		if (searchFor != null && searchForValue != null) {
			addDescriptionFilter(root, predicates, cBuilder, searchFor, searchForValue);
			addLevelFilter(root, predicates, cBuilder, searchFor, searchForValue);
			addSourceFilter(root, predicates, cBuilder, searchFor, searchForValue);
		}

		Predicate where = cBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		return where;
	}

	private void addOrder(CriteriaQuery<LogAggregate> cQuery, CriteriaBuilder cBuilder, Root<LogAggregate> root,
			OrderByEnum orderBy) {
		if (orderBy != null) {
			cQuery.orderBy(cBuilder.desc(root.get(orderBy.getField())));
		}
	}

	private void addEnvironmentFilter(Root<LogAggregate> root, List<Predicate> predicates, CriteriaBuilder cBuilder,
			EnvironmentEnum environment) {
		if (environment != null) {
			Path<String> path = root.get("environment");
			predicates.add(cBuilder.equal(path, environment));
		}
	}

	private void addDescriptionFilter(Root<LogAggregate> root, List<Predicate> predicates, CriteriaBuilder cBuilder,
			SearchForEnum searchFor, String searchForValue) {
		if (searchFor.equals(SearchForEnum.DESCRIPTION)) {
			Path<String> titlePath = root.get("title");
			Predicate titleSearch = cBuilder.like(cBuilder.lower(titlePath), addPercentCharacter(searchForValue));

			Path<String> descriptionPath = root.get("description");
			Predicate descriptionSearch = cBuilder.like(cBuilder.lower(descriptionPath),
					addPercentCharacter(searchForValue));

			predicates.add(cBuilder.or(titleSearch, descriptionSearch));
		}
	}

	private void addLevelFilter(Root<LogAggregate> root, List<Predicate> predicates, CriteriaBuilder cBuilder,
			SearchForEnum searchFor, String searchForValue) {
		if (searchFor.equals(SearchForEnum.LEVEL)) {
			Optional<Level> levelOptional = Level.getEnumByValue(searchForValue);
			if (levelOptional.isPresent()) {
				Path<String> path = root.get("level");
				Predicate search = cBuilder.equal(path, levelOptional.get());

				predicates.add(search);
			}
		}
	}

	private void addStatusFilter(Root<LogAggregate> root, List<Predicate> predicates, CriteriaBuilder cBuilder,
			StatusEnum status) {
		Path<String> path = root.get("status");
		Predicate search = cBuilder.equal(path, status);

		predicates.add(search);
	}

	private void addSourceFilter(Root<LogAggregate> root, List<Predicate> predicates, CriteriaBuilder cBuilder,
			SearchForEnum searchFor, String searchForValue) {
		if (searchFor.equals(SearchForEnum.SOURCE)) {
			Path<String> path = root.get("source");
			Predicate search = cBuilder.like(cBuilder.lower(path), addPercentCharacter(searchForValue));

			predicates.add(search);
		}
	}

	private String addPercentCharacter(String title) {
		return String.format("%%%s%%", title.toLowerCase());
	}
}
