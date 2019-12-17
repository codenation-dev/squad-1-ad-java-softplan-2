package br.com.codenation.softlog.util.exceptionhandler;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long timestamp;
    private Integer status;
    private String message;
    private String path;

    public StandardError(Integer status, String message, String path) {
        super();
        this.timestamp = Instant.now().toEpochMilli();
        this.status = status;
        this.message = message;
        this.path = path;
    }

}
