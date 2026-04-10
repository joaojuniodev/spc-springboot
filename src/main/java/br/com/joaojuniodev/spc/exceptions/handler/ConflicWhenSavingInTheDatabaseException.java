package br.com.joaojuniodev.spc.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflicWhenSavingInTheDatabaseException extends RuntimeException {
    public ConflicWhenSavingInTheDatabaseException(String message) {
        super(message);
    }
}
