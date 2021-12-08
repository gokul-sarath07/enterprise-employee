package com.enterprise.service.model;

import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class Error {
    private String field;
    private String defaultMessage;

    public Error() { }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public List<Error> getErrorDetails(List<ObjectError> errors) {
        List<Error> errorList = new ArrayList<>();
        errors.forEach(
                e -> {
                    Error error = new Error();
                    String code = e.getCodes()[0];
                    String fieldName = code.substring(code.lastIndexOf(".") + 1);
                    error.setField(fieldName);
                    error.setDefaultMessage(e.getDefaultMessage());
                    errorList.add(error);
                }
        );
        return errorList;
    }
}
