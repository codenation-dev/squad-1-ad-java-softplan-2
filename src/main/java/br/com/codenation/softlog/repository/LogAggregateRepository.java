package br.com.codenation.softlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codenation.softlog.model.LogAggregate;

public interface LogAggregateRepository extends JpaRepository<LogAggregate, Long> {

	List<LogAggregate> findByTitle(String title);

}