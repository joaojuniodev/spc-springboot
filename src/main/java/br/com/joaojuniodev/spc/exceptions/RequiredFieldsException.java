package br.com.joaojuniodev.spc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class RequiredFieldsException extends RuntimeException {
    public RequiredFieldsException(String message) {
        super(message);
    }
}
