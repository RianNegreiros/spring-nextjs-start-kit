package br.com.riannegreiros.backend.util.exceptions;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends ApiException {
    public EmailAlreadyExistsException(String email) {
        super(HttpStatus.BAD_REQUEST,
                String.format("Email '%s' is already registered", email));
    }
}
