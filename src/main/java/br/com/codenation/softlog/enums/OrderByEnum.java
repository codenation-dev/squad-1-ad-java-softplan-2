package br.com.codenation.softlog.enums;

import lombok.Getter;

@Getter
public enum OrderByEnum {
	LEVEL("level"), FREQUENCY("events");

	private String field;

	private OrderByEnum(String field) {
		this.field = field;
	}
}
