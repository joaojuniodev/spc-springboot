package br.com.joaojuniodev.spc.exceptions.handler;

import br.com.joaojuniodev.spc.data.dtos.exceptions.ExceptionResponse;
import br.com.joaojuniodev.spc.exceptions.ConflictInTheDatabaseException;
import br.com.joaojuniodev.spc.exceptions.NotFoundException;
import br.com.joaojuniodev.spc.exceptions.RequiredFieldsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestController
@RestControllerAdvice
public class CustomizedExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
            ex.getMessage(),
            request.getDescription(true),
            new Date()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
            ex.getMessage(),
            request.getDescription(true),
            new Date()
        );
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }

    @ExceptionHandler(ConflictInTheDatabaseException.class)
    public final ResponseEntity<ExceptionResponse> handleConflictInTheDatabaseException(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
            ex.getMessage(),
            request.getDescription(true),
            new Date()
        );
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }

    @ExceptionHandler(RequiredFieldsException.class)
    public final ResponseEntity<ExceptionResponse> handleRequiredFieldsException(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
            ex.getMessage(),
            request.getDescription(true),
            new Date()
        );
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }
}