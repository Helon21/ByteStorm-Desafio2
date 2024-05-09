package com.bytestorm.cursos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class CursoInativoException extends RuntimeException {

    public CursoInativoException(String msg) {
        super(msg);
    }
}