package br.com.codenation.softlog.model;

import br.com.codenation.softlog.model.enums.Environment;
import br.com.codenation.softlog.model.enums.Level;
import br.com.codenation.softlog.model.enums.Status;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

public class LogAggregate {

    @Enumerated(EnumType.STRING)
    private Level level;

    private String source;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Environment environment;

    @Enumerated(EnumType.STRING)
    private Status status;

    private User user;

    private LocalDateTime createdAt;

    private Long events;

}
