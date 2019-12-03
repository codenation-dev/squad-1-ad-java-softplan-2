package br.com.codenation.softlog.repository;

import br.com.codenation.softlog.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {

    Log save(Log log);
}
