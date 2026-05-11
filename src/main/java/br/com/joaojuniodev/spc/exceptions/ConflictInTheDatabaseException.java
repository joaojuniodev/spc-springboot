package br.com.joaojuniodev.spc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictInTheDatabaseException extends RuntimeException {
    public ConflictInTheDatabaseException(String message) {
        super(message);
    }
}
