package br.com.codenation.softlog.dto.response;

import br.com.codenation.softlog.model.enums.EnvironmentEnum;
import br.com.codenation.softlog.model.enums.Level;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({"level", "source", "title", "description", "environment", "createdAt"})
public class LogResponseDTO {

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(
            value = "Log severity level",
            position = 1,
            example = "ERROR",
            allowableValues = "INFO, DEBUG, WARNING, ERROR, FATAL")
    private Level level;

    @ApiModelProperty(
            value = "Log source identifier or application name",
            position = 2,
            example = "172.16.254.1")
    private String source;

    @ApiModelProperty(
            value = "Log title message",
            position = 3,
            example = "Error:(42, 52) java: cannot find symbol")
    private String title;

    @ApiModelProperty(
            value = "Log description message",
            position = 4,
            example = "location: class br.com.codenation.my-app:AuthService")
    private String description;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(
            value = "Application running environment",
            position = 5,
            example = "DEVELOPMENT",
            allowableValues = "DEVELOPMENT, HOMOLOGATION , PRODUCTION")
    private EnvironmentEnum environment;

    @CreatedDate
    @ApiModelProperty(
            value = "Log generation timestamp",
            position = 6,
            example = "2019-12-03T15:25:48.9120524")
    private LocalDateTime createdAt;
}
