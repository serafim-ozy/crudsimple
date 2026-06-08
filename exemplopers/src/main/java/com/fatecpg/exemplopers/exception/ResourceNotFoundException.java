package com.fatecpg.exemplopers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Lancada quando um recurso buscado por ID nao existe.
 * Anotada com @ResponseStatus para garantir 404 mesmo sem passar pelo handler.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensagem) {
        super(mensagem);
    }

    public ResourceNotFoundException(String recurso, Long id) {
        super(String.format("%s com id %d nao encontrado(a).", recurso, id));
    }
}
