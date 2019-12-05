package br.com.codenation.softlog.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PageDTO<T> {

	private List<T> registers;
	private Long totalRegisters;

}
