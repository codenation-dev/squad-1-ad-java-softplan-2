package br.com.codenation.squad1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.codenation.softlog.model.enums.Environment;
import br.com.codenation.softlog.repository.LogAggregateRepository;

public class LogResourceTests {

    @Autowired
    private LogAggregateRepository logAggregateRepository;

    @Test
    public void testGetAllLogs() {
        logAggregateRepository.findAll();
    }

    @Test
    public void testGetAllLogsByEnvironment() {
        logAggregateRepository.findByEnvironment(Environment.DEVELOPMENT);
    }
}
