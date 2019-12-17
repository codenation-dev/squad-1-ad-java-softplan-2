package br.com.codenation.softlog.dto.request;

import br.com.codenation.softlog.model.enums.EnvironmentEnum;
import br.com.codenation.softlog.model.enums.Level;
import br.com.codenation.softlog.model.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@JsonPropertyOrder({ "apiKey", "source", "title", "description", "environment", "level" })
public class LogRequestDTO {

    @NotBlank
    @ApiModelProperty(
            value = "User API key",
            position = 1,
            example = "f0fb85c6-6165-4972-bd33-0369ebffc6ac",
            required = true)
    private String apiKey;

    @NotBlank
    @Size(max = 50)
    @ApiModelProperty(
            value = "Log source identifier or application name",
            position = 2,
            example = "172.16.254.1",
            required = true)
    private String source;

    @NotBlank
    @Size(max = 255)
    @ApiModelProperty(
            value = "Log title message",
            position = 3,
            example = "Error:(42, 52) java: cannot find symbol",
            required = true)
    private String title;

    @NotBlank
    @Size(max = 500)
    @ApiModelProperty(
            value = "Log description message",
            position = 4,
            example = "location: class br.com.codenation.my-app:AuthService",
            required = true)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(
            value = "Application running environment",
            position = 5,
            example = "DEVELOPMENT",
            allowableValues = "DEVELOPMENT, HOMOLOGATION , PRODUCTION",
            required = true)
    private EnvironmentEnum environment;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(
            value = "Log severity level",
            position = 6,
            example = "ERROR",
            allowableValues = "INFO, DEBUG, WARNING, ERROR, FATAL",
            required = true)
    private Level level;

    @JsonIgnore
    private StatusEnum status = StatusEnum.ACTIVE;

}
