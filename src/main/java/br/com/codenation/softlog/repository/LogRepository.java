package br.com.codenation.softlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codenation.softlog.model.Log;
import br.com.codenation.softlog.model.enums.EnvironmentEnum;
import br.com.codenation.softlog.model.enums.Level;
import br.com.codenation.softlog.model.enums.StatusEnum;

public interface LogRepository extends JpaRepository<Log, Long> {

	List<Log> findByTitleAndDescriptionAndLevelAndApiKeyAndSourceAndStatusAndEnvironment(String title,
			String description, Level level, String apiKey, String source, StatusEnum status,
			EnvironmentEnum environment);

	void deleteByTitleAndDescriptionAndLevelAndApiKeyAndSourceAndStatusAndEnvironment(String title, String description,
			Level level, String apiKey, String source, StatusEnum status, EnvironmentEnum environment);
}
