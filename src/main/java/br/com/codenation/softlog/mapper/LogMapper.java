package br.com.codenation.softlog.mapper;

import br.com.codenation.softlog.dto.request.LogRequestDTO;
import br.com.codenation.softlog.dto.response.LogResponseDTO;
import br.com.codenation.softlog.model.Log;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogMapper {

    Log map(LogRequestDTO dto);

    LogResponseDTO map(Log log);
}
