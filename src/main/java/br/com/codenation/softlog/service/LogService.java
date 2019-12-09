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

    private final LogRepository logRepository;
    private final LogMapper logMapper;
    private final LogAggregateMapper aggregateMapper;
    private final UserService userService;
    private final EntityManager entityManager;

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

    public void remove(final EnvironmentEnum environment, final SearchForEnum searchFor, final String searchForValue, final StatusEnum status) {
        final CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Log> cQuery = cBuilder.createQuery(Log.class);
        final Root<Log> root = cQuery.from(Log.class);

        final Predicate where = addFiltersToRemoveOrArchive(root, cBuilder, environment, searchFor, searchForValue, status);

        cQuery.select(root).where(where);

        final TypedQuery<Log> query = entityManager.createQuery(cQuery);

        // execute query, get Logs
        query.getResultList();
    }

    private Predicate addFiltersToRemoveOrArchive(final Root<Log> root, final CriteriaBuilder cBuilder, final EnvironmentEnum environment, final SearchForEnum searchFor, final String searchForValue,
            final StatusEnum status) {
        final List<Predicate> predicates = new ArrayList<>();

        addStatusFilterForRemoveOrArchive(root, predicates, cBuilder, status);
        addEnvironmentFilterForRemoveOrArchive(root, predicates, cBuilder, environment);
        if (searchFor != null && searchForValue != null) {
            addDescriptionFilterForRemoveOrArchive(root, predicates, cBuilder, searchFor, searchForValue);
            addLevelFilterForRemoveOrArchive(root, predicates, cBuilder, searchFor, searchForValue);
            addSourceFilterForRemoveOrArchive(root, predicates, cBuilder, searchFor, searchForValue);
        }

        final Predicate where = cBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        return where;
    }

    public PageDTO<LogAggregateResponseDTO> searchLogs(final EnvironmentEnum environment, final OrderByEnum orderBy,
            final SearchForEnum searchFor, final String searchForValue, final StatusEnum status, final Integer startPage, final Integer pageSize) {

        final List<LogAggregateResponseDTO> logsDTO = searchLogsData(environment, orderBy, searchFor, searchForValue, status,
                startPage, pageSize);

        final Long logsCount = searchLogsCount(environment, searchFor, searchForValue, status);

        // return datas
        return new PageDTO<LogAggregateResponseDTO>(logsDTO, logsCount);
    }

    private Long searchLogsCount(final EnvironmentEnum environment, final SearchForEnum searchFor, final String searchForValue,
            final StatusEnum status) {

        final CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> cQuery = cBuilder.createQuery(Long.class);
        final Root<LogAggregate> root = cQuery.from(LogAggregate.class);

        final Predicate where = addFilters(root, cBuilder, environment, searchFor, searchForValue, status);

        cQuery.select(cBuilder.count(root)).where(where);

        final TypedQuery<Long> query = entityManager.createQuery(cQuery);

        // execute query, get Logs
        final Long count = query.getSingleResult();

        return count;
    }

    private List<LogAggregateResponseDTO> searchLogsData(final EnvironmentEnum environment, final OrderByEnum orderBy,
            final SearchForEnum searchFor, final String searchForValue, final StatusEnum status, final Integer startPage, final Integer pageSize) {

        final CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<LogAggregate> cQuery = cBuilder.createQuery(LogAggregate.class);
        final Root<LogAggregate> root = cQuery.from(LogAggregate.class);

        final Predicate where = addFilters(root, cBuilder, environment, searchFor, searchForValue, status);

        cQuery.select(root).where(where);

        addOrder(cQuery, cBuilder, root, orderBy);

        // pagination
        final TypedQuery<LogAggregate> query = entityManager.createQuery(cQuery);
        query.setFirstResult(startPage);
        query.setMaxResults(pageSize);

        // execute query, get Logs
        final List<LogAggregate> logs = query.getResultList();
        // tranform to DTO
        final List<LogAggregateResponseDTO> logsDTO = aggregateMapper.map(logs);

        return logsDTO;
    }

    private Predicate addFilters(final Root<LogAggregate> root, final CriteriaBuilder cBuilder, final EnvironmentEnum environment,
            final SearchForEnum searchFor, final String searchForValue, final StatusEnum status) {

        final List<Predicate> predicates = new ArrayList<>();

        addStatusFilter(root, predicates, cBuilder, status);
        addEnvironmentFilter(root, predicates, cBuilder, environment);
        if (searchFor != null && searchForValue != null) {
            addDescriptionFilter(root, predicates, cBuilder, searchFor, searchForValue);
            addLevelFilter(root, predicates, cBuilder, searchFor, searchForValue);
            addSourceFilter(root, predicates, cBuilder, searchFor, searchForValue);
        }

        final Predicate where = cBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        return where;
    }

    private void addOrder(final CriteriaQuery<LogAggregate> cQuery, final CriteriaBuilder cBuilder, final Root<LogAggregate> root,
            final OrderByEnum orderBy) {
        if (orderBy != null) {
            cQuery.orderBy(cBuilder.desc(root.get(orderBy.getField())));
        }
    }

    private void addEnvironmentFilter(final Root<LogAggregate> root, final List<Predicate> predicates, final CriteriaBuilder cBuilder,
            final EnvironmentEnum environment) {
        if (environment != null) {
            final Path<String> path = root.get("environment");
            predicates.add(cBuilder.equal(path, environment));
        }
    }

    private void addDescriptionFilter(final Root<LogAggregate> root, final List<Predicate> predicates, final CriteriaBuilder cBuilder,
            final SearchForEnum searchFor, final String searchForValue) {
        if (searchFor.equals(SearchForEnum.DESCRIPTION)) {
            final Path<String> titlePath = root.get("title");
            final Predicate titleSearch = cBuilder.like(cBuilder.lower(titlePath), addPercentCharacter(searchForValue));

            final Path<String> descriptionPath = root.get("description");
            final Predicate descriptionSearch = cBuilder.like(cBuilder.lower(descriptionPath),
                    addPercentCharacter(searchForValue));

            predicates.add(cBuilder.or(titleSearch, descriptionSearch));
        }
    }

    private void addLevelFilter(final Root<LogAggregate> root, final List<Predicate> predicates, final CriteriaBuilder cBuilder,
            final SearchForEnum searchFor, final String searchForValue) {
        if (searchFor.equals(SearchForEnum.LEVEL)) {
            final Optional<Level> levelOptional = Level.getEnumByValue(searchForValue);
            if (levelOptional.isPresent()) {
                final Path<String> path = root.get("level");
                final Predicate search = cBuilder.equal(path, levelOptional.get());

                predicates.add(search);
            }
        }
    }

    private void addStatusFilter(final Root<LogAggregate> root, final List<Predicate> predicates, final CriteriaBuilder cBuilder,
            final StatusEnum status) {
        final Path<String> path = root.get("status");
        final Predicate search = cBuilder.equal(path, status);

        predicates.add(search);
    }

    private void addSourceFilter(final Root<LogAggregate> root, final List<Predicate> predicates, final CriteriaBuilder cBuilder,
            final SearchForEnum searchFor, final String searchForValue) {
        if (searchFor.equals(SearchForEnum.SOURCE)) {
            final Path<String> path = root.get("source");
            final Predicate search = cBuilder.like(cBuilder.lower(path), addPercentCharacter(searchForValue));

            predicates.add(search);
        }
    }

    private void addSourceFilterForRemoveOrArchive(final Root<Log> root, final List<Predicate> predicates, final CriteriaBuilder cBuilder, final SearchForEnum searchFor, final String searchForValue) {
        if (searchFor.equals(SearchForEnum.SOURCE)) {
            final Path<String> path = root.get("source");
            final Predicate search = cBuilder.like(cBuilder.lower(path), addPercentCharacter(searchForValue));

            predicates.add(search);
        }

    }

    private void addLevelFilterForRemoveOrArchive(final Root<Log> root, final List<Predicate> predicates, final CriteriaBuilder cBuilder, final SearchForEnum searchFor, final String searchForValue) {
        if (searchFor.equals(SearchForEnum.LEVEL)) {
            final Optional<Level> levelOptional = Level.getEnumByValue(searchForValue);
            if (levelOptional.isPresent()) {
                final Path<String> path = root.get("level");
                final Predicate search = cBuilder.equal(path, levelOptional.get());

                predicates.add(search);
            }
        }
    }

    private void addDescriptionFilterForRemoveOrArchive(final Root<Log> root, final List<Predicate> predicates, final CriteriaBuilder cBuilder, final SearchForEnum searchFor,
            final String searchForValue) {
        if (searchFor.equals(SearchForEnum.DESCRIPTION)) {
            final Path<String> titlePath = root.get("title");
            final Predicate titleSearch = cBuilder.like(cBuilder.lower(titlePath), addPercentCharacter(searchForValue));

            final Path<String> descriptionPath = root.get("description");
            final Predicate descriptionSearch = cBuilder.like(cBuilder.lower(descriptionPath),
                    addPercentCharacter(searchForValue));

            predicates.add(cBuilder.or(titleSearch, descriptionSearch));
        }
    }

    private void addEnvironmentFilterForRemoveOrArchive(final Root<Log> root, final List<Predicate> predicates, final CriteriaBuilder cBuilder, final EnvironmentEnum environment) {
        if (environment != null) {
            final Path<String> path = root.get("environment");
            predicates.add(cBuilder.equal(path, environment));
        }
    }

    private void addStatusFilterForRemoveOrArchive(final Root<Log> root, final List<Predicate> predicates, final CriteriaBuilder cBuilder, final StatusEnum status) {
        final Path<String> path = root.get("status");
        final Predicate search = cBuilder.equal(path, status);

        predicates.add(search);
    }

    private String addPercentCharacter(final String title) {
        return String.format("%%%s%%", title.toLowerCase());
    }
}
