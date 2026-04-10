package it.codeaveclionel.bookmanager.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictExeption extends RuntimeException {
    public ConflictExeption(String message) {
        super(message);
    }
}
