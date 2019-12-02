package br.com.codenation.softlog.util.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String message, String path) {
        super(status, message, path);
    }

    public List<FieldMessage> getErrors() {
        return this.errors;
    }

    public void addError(String fieldName, String messagem) {
        this.errors.add(new FieldMessage(fieldName, messagem));
    }
}
