package com.bytestorm.cursos.controller;

import com.bytestorm.cursos.entity.Curso;

import com.bytestorm.cursos.mapper.CursoMapper;
import com.bytestorm.cursos.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bytestorm.cursos.record.CursoRecord.CursoRequisicaoDTO;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cursos/")
public class CursoController {

    private final CursoService cursoService;

    @GetMapping("buscar-cursos")
    public ResponseEntity<List<CursoRequisicaoDTO>> buscarTodosCursos() {
        List<Curso> cursos = cursoService.buscarTodosCursos();
        List<CursoRequisicaoDTO> cursosDTO = cursos.stream().map(CursoMapper::toCursoRequisicaoDTO).collect(Collectors.toList());
        return ResponseEntity.ok(cursosDTO);
    }

    @GetMapping("buscar-curso-id/{id}")
    public ResponseEntity<CursoRequisicaoDTO> buscarCursoPorId(@PathVariable Long id) {
        Curso curso = cursoService.buscarCursoPorId(id);
        CursoRequisicaoDTO cursoDTO = CursoMapper.toCursoRequisicaoDTO(curso);
        return ResponseEntity.ok(cursoDTO);
    }

    @PostMapping("cadastrar")
    public ResponseEntity<CursoRequisicaoDTO> cadastrarCurso(@RequestBody CursoRequisicaoDTO dto) {
        Curso curso = CursoMapper.toEntity(dto);
        cursoService.cadastrarCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(CursoMapper.toCursoRequisicaoDTO(curso));
    }

    @PatchMapping("alterar-professor/{id}")
    public ResponseEntity<CursoRequisicaoDTO> alterarProfessor(@PathVariable Long id, @RequestBody CursoRequisicaoDTO dto) {
        String professor = dto.professor();
        Curso novoProfessor = cursoService.alterarProfessor(id, professor);
        CursoRequisicaoDTO cursoAtualizado = CursoMapper.toCursoRequisicaoDTO(novoProfessor);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoAtualizado);
    }

    @PatchMapping("inabilitar-curso/{id}")
    public ResponseEntity<CursoRequisicaoDTO> inabilitarCurso(@PathVariable Long id) {
        Curso curso = cursoService.inabilitarCurso(id);
        CursoRequisicaoDTO cursoDTO = CursoMapper.toCursoRequisicaoDTO(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoDTO);
    }
}