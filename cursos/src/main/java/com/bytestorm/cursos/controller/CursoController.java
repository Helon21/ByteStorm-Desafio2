package com.bytestorm.cursos.controller;

import com.bytestorm.cursos.entity.Curso;
import com.bytestorm.cursos.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cursos/")
public class CursoController {

    private final CursoService cursoService;

    @GetMapping("buscar-cursos")
    public ResponseEntity<List<Curso>> buscarTodosCursos() {
        List<Curso> cursos = cursoService.buscarTodosCursos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("buscar-curso-id/{id}")
    public ResponseEntity<Curso> buscarCursoPorId(@PathVariable Long id) {
        Curso curso = cursoService.buscarCursoPorId(id);
        return ResponseEntity.ok(curso);
    }

    @PostMapping("cadastrar")
    public ResponseEntity<Curso> cadastrarCurso(@RequestBody Curso curso) {
        Curso novoCurso = cursoService.cadastrarCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCurso);
    }

    @PatchMapping("alterar-professor/{id}")
    public ResponseEntity<Curso> alterarProfessor(@PathVariable Long id, @RequestBody String professor) {
        Curso novoProfessor = cursoService.alterarProfessor(id, professor);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(novoProfessor);
    }

    @PatchMapping("inabilitar-curso/{id}")
    public ResponseEntity<Void> inabilitarCurso(@PathVariable Long id) {
        cursoService.inabilitarCurso(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}