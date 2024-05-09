package com.bytestorm.cursos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NomeCursoRepetidoException extends RuntimeException {

    public NomeCursoRepetidoException(String msg) {
        super(msg);
    }
}
