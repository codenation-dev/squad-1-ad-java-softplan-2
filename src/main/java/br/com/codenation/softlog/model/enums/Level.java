package br.com.codenation.softlog.model.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Level {

	INFO, WARN, ERROR, DEBUG, FATAL;

	public static Optional<Level> getEnumByValue(String value) {
		return Arrays.asList(values()).stream().filter(v -> v.name().equalsIgnoreCase(value)).findAny();
	}
}
