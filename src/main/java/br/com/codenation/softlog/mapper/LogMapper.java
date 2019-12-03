package br.com.codenation.softlog.mapper;

import br.com.codenation.softlog.dto.request.LogRequestDTO;
import br.com.codenation.softlog.dto.response.LogResponseDTO;
import br.com.codenation.softlog.model.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface LogMapper {

    @Mappings({
            @Mapping(target = "apiKey", source = "apiKey"),
            @Mapping(target = "source", source = "source"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "environment", source = "environment"),
            @Mapping(target = "level", source = "level"),
            @Mapping(target = "status", source = "status")
    })
    Log map(LogRequestDTO dto);

    @Mappings({
            @Mapping(target = "level", source = "level"),
            @Mapping(target = "source", source = "source"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "environment", source = "environment"),
            @Mapping(target = "createdAt", source = "createdAt")
    })
    LogResponseDTO map(Log log);
}
