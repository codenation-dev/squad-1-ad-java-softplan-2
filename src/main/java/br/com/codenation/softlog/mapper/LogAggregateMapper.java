package br.com.codenation.softlog.mapper;

import br.com.codenation.softlog.dto.response.LogAggregateResponseDTO;
import br.com.codenation.softlog.model.LogAggregate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogAggregateMapper {

    LogAggregateResponseDTO map(LogAggregate logAggregate);

    List<LogAggregateResponseDTO> map(List<LogAggregate> logAggregate);
}
